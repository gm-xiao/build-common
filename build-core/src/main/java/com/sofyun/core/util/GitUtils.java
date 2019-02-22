package com.sofyun.core.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.http.server.GitServlet;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.*;

/**
 * @ClassName GitUtils
 * @Description GitUtils
 * @Author gm
 * @Date 2019/2/21 17:46
 **/
public class GitUtils {

    /**
     * 创建本地仓库
     * @param repoPath
     * @return
     * @throws IOException
     */
    public Repository createNewRepository(String repoPath) throws IOException {
        // prepare a new folder
        File localPath = new File(repoPath);

        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();

        return repository;
    }

    /**
     * 从远程git仓库拉取代码
     * @param user
     * @param pwd
     * @param url
     * @param branch
     * @param local
     * @return
     */
    public boolean setupRepository(String user, String pwd, String url, String branch, String local){
        boolean setupRepositoryFlag=true;
        branch = (StringUtils.isBlank(branch))?"master":branch;
        try {
            UsernamePasswordCredentialsProvider provider =new UsernamePasswordCredentialsProvider(user, pwd);
            if(StringUtils.isBlank(user) || StringUtils.isBlank(pwd)){
                Git git =Git.cloneRepository().setURI(url)
                        .setBranch(branch)
                        .setDirectory(new File(local))
                        .call();
            }else{
                Git git =Git.cloneRepository().setURI(url)
                        .setBranch(branch)
                        .setDirectory(new File(local))
                        .setCredentialsProvider(provider)
                        .call();
            }
        } catch (Exception e) {
            e.printStackTrace();
            setupRepositoryFlag=false;
        }
        return setupRepositoryFlag;
    }

    /**
     * 拉取分支代码
     * @param gitConfig
     * @param remoteBranchName
     * @return
     */
    public static boolean pull(String gitConfig, String remoteBranchName) {

        boolean pullFlag = true;
        try (Git git = Git.open(new File(gitConfig));) {
            git.pull()
                    .setRemoteBranchName(remoteBranchName)
                    .call();
        } catch (Exception e) {
            e.printStackTrace();
            pullFlag = false;
        }
        return pullFlag;
    }

    /**
     * 提交并推送代码至远程服务器
     * @param filePath 提交文件路径(相对路径)
     * @param desc 提交描述
     * @return
     */
    public static boolean commitAndPush(String gitConfig, String user, String pwd, String filePath,String desc){

        boolean commitAndPushFlag=true;
        try ( Git git = Git.open( new File(gitConfig) );) {
            UsernamePasswordCredentialsProvider provider =new UsernamePasswordCredentialsProvider(user,pwd);
            git.add().addFilepattern(filePath).call();
            git.commit().setMessage(desc).call();
            if(StringUtils.isBlank(user) || StringUtils.isBlank(pwd)){
                git.push().setCredentialsProvider(provider).call();
            }else{
                git.push().call();
            }
        }catch (Exception e) {
            e.printStackTrace();
            commitAndPushFlag=false;
        }
        return commitAndPushFlag;
    }

    /**
     * 提交代码到本地仓库
     * @param comment 提交git内容描述
     * @return
     */
    public static boolean commitFile(String gitConfig, String comment) {
        boolean commitFileFlag = true;
        try (Git git = Git.open(new File(gitConfig));) {
            git.commit().setMessage(comment).call();
        } catch (Exception e) {
            e.printStackTrace();
            commitFileFlag = false;
        }
        return commitFileFlag;
    }

    /**
     * 生成文件写内容
     * @param content
     * @param filePath
     */
    @SuppressWarnings("unused")
    private static boolean createFile(String content,String filePath){

        boolean createFileFlag=true;
        File file =new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                createFileFlag=false;
            }
        }
        try(BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),Charsets.UTF_8));) {
            bw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFileFlag=false;
        } catch (IOException e) {
            e.printStackTrace();
            createFileFlag=false;
        }
        return createFileFlag;
    }

    /**
     * 删除文件
     * @param file
     */
    private static void deleteFolder(File file){
        if(file.isFile() || file.list().length==0){
            file.delete();
        }else{
            File[] files = file.listFiles();
            for(int i=0;i<files.length;i++){
                deleteFolder(files[i]);
                files[i].delete();
            }
        }
    }

    private static Server configureAndStartHttpServer(GitServlet gs) throws Exception {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        ServletHolder holder = new ServletHolder(gs);

        handler.addServletWithMapping(holder, "/*");

        server.start();
        return server;
    }

    private static void populateRepository(Repository repository) throws IOException, GitAPIException {
        try (Git git = new Git(repository)) {
            File myfile = new File(repository.getDirectory().getParent(), "testfile");
            if(!myfile.createNewFile()) {
                throw new IOException("Could not create file " + myfile);
            }

            git.add().addFilepattern("testfile").call();

            System.out.println("Added file " + myfile + " to repository at " + repository.getDirectory());

            git.commit().setMessage("Test-Checkin").call();
        }
    }


}


