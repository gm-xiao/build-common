package com.sofyun.admin.service.impl;

import com.sofyun.admin.domain.DataBase;
import com.sofyun.admin.domain.request.database.SaveBO;
import com.sofyun.admin.mapper.DataBaseMapper;
import com.sofyun.admin.service.DataBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.constant.BuildConstant;
import com.sofyun.core.util.DBUtils;
import com.sofyun.core.util.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public DataBase insert(SaveBO saveBO) {
        DataBase dataBase = new DataBase();
        BeanUtils.copyProperties(saveBO, dataBase);
        dataBase.setId(idUtils.create());
        if (this.save(dataBase)){
            String jdbcUrl = BuildConstant.JDBC_DATA_PATH
                    .replace("IP", dataBase.getIp())
                    .replace("PORT", dataBase.getPort().toString())
                    .replace("USER", dataBase.getUsername())
                    .replace("PWD", dataBase.getPassword());
            dbUtils.createDataBase(jdbcUrl, dataBase.getEnName());
        }
        return dataBase;
    }
}
