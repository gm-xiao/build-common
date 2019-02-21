package com.sofyun.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofyun.admin.domain.DataTable;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.QueryBO;
import com.sofyun.admin.domain.request.datatable.SaveBO;
import com.sofyun.admin.domain.request.datatable.UpdateBO;
import com.sofyun.admin.mapper.DataTableMapper;
import com.sofyun.admin.service.DataTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.util.DBUtils;
import com.sofyun.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据表 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Service
public class DataTableServiceImpl extends ServiceImpl<DataTableMapper, DataTable> implements DataTableService {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private DataTableMapper dataTableMapper;

    @Autowired
    private DBUtils dbUtils;

    private QueryWrapper<DataTable> createQuery(DataTable dataTable){
        QueryWrapper<DataTable> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dataTable.getDataBase())){
            queryWrapper.eq("data_base", dataTable.getDataBase());
        }
        return queryWrapper;
    }

    @Override
    public DataTable insert(SaveBO saveBO) {
        DataTable dataTable = new DataTable();
        BeanUtils.copyProperties(saveBO, dataTable);
        dataTable.setId(idUtils.create());
        this.save(dataTable);
        return dataTable;
    }

    @Override
    public DataTable update(UpdateBO updateBO) {
        DataTable dataTable = new DataTable();
        BeanUtils.copyProperties(updateBO, dataTable);
        this.save(dataTable);
        return dataTable;
    }

    @Override
    public void delete(DeleteBO deleteBO) {
        dataTableMapper.deleteById(deleteBO.getId());
    }

    @Override
    public List<DataTable> list(DataTable dataTable) {
        return dataTableMapper.selectList(createQuery(dataTable));
    }

    @Override
    public List<DataTable> list(QueryBO queryBO) {
        return null;
    }
}
