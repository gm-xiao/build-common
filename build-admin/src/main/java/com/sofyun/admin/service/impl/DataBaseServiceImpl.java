package com.sofyun.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.admin.constant.BuildState;
import com.sofyun.admin.domain.DataBase;
import com.sofyun.admin.domain.DataColumn;
import com.sofyun.admin.domain.DataTable;
import com.sofyun.admin.domain.request.database.InitBO;
import com.sofyun.admin.domain.request.database.SaveBO;
import com.sofyun.admin.mapper.DataBaseMapper;
import com.sofyun.admin.service.DataBaseService;
import com.sofyun.admin.service.DataColumnService;
import com.sofyun.admin.service.DataTableService;
import com.sofyun.admin.service.ProjectService;
import com.sofyun.core.constant.BuildConstant;
import com.sofyun.core.constant.Status;
import com.sofyun.core.exception.BaseException;
import com.sofyun.core.util.DBModel;
import com.sofyun.core.util.DBUtils;
import com.sofyun.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据库 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Service
public class DataBaseServiceImpl extends ServiceImpl<DataBaseMapper, DataBase> implements DataBaseService {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private DBUtils dbUtils;

    @Autowired
    private DataBaseMapper dataBaseMapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DataTableService dataTableService;

    @Autowired
    private DataColumnService dataColumnService;


    @Override
    public DataBase insert(SaveBO saveBO) {
        DataBase dataBase = new DataBase();
        BeanUtils.copyProperties(saveBO, dataBase);
        dataBase.setId(idUtils.create());
        this.save(dataBase);

        projectService.updateBuildState(dataBase.getProject(), BuildState.CREATE_DATE_BASE.value());

        return dataBase;
    }

    @Override
    public Boolean init(InitBO initBO) throws BaseException {
        Boolean result = false;
        // 1.获取数据库信息
        DataBase dataBase = dataBaseMapper.selectById(initBO.getId());
        if (null == dataBase){
            throw new BaseException(Status.ERROR.getCode(), "参数错误");
        }

        // 2.获取数据表信息
        DataTable dataTable = new DataTable();
        dataTable.setDataBase(initBO.getId());
        List<DataTable> dataTables = dataTableService.list(dataTable);


        // 3.获取表字段信息
        for (DataTable table : dataTables){
            QueryWrapper<DataColumn> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("data_table", table.getId());
            List<DataColumn> dataColumns = dataColumnService.list(queryWrapper);
            table.setDataColumns(dataColumns);
        }

        // 4.组合数据库对象
        DBModel model = new DBModel();
        BeanUtils.copyProperties(dataBase, model);
        List<DBModel.Table> tables = new ArrayList<>();
        for (DataTable tem : dataTables){
            DBModel.Table table = new DBModel.Table();
            table.setName(tem.getName());
            table.setEnName(tem.getEnName());
            List<DBModel.Column> columns = new ArrayList<>();
            for (DataColumn dataColumn : tem.getDataColumns()){
                DBModel.Column column = new DBModel.Column();
                BeanUtils.copyProperties(dataColumn, column);
                columns.add(column);
            }
            table.setColumns(columns);
            tables.add(table);
        }
        model.setTables(tables);

        // 5.初始化数据库
        dbUtils.init(model);

        boolean ok = true;
        while (ok){
            String status = DBUtils.status.get() + "";
            if ("4".equals(status)){
                ok = false;
            }else if ("5".equals(status)){
                ok = false;
                result = true;
            }
        }

        // 6.修改项目状态
        if (result){
            projectService.updateBuildState(dataBase.getProject(), BuildState.INIT_DATE_BASE.value());
        }

        return result;
    }
}
