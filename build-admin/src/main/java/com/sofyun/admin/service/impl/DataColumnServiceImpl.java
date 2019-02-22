package com.sofyun.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.admin.domain.DataColumn;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.datacolumn.SaveBO;
import com.sofyun.admin.domain.request.datacolumn.UpdateBO;
import com.sofyun.admin.mapper.DataColumnMapper;
import com.sofyun.admin.service.DataColumnService;
import com.sofyun.core.util.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据表字段 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Service
public class DataColumnServiceImpl extends ServiceImpl<DataColumnMapper, DataColumn> implements DataColumnService {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private DataColumnMapper dataColumnMapper;

    @Override
    public DataColumn insert(SaveBO saveBO) {
        DataColumn dataColumn = new DataColumn();
        BeanUtils.copyProperties(saveBO, dataColumn);
        dataColumn.setId(idUtils.create());
        this.save(dataColumn);
        return dataColumn;
    }

    @Override
    public DataColumn update(UpdateBO updateBO) {
        DataColumn dataColumn = new DataColumn();
        BeanUtils.copyProperties(updateBO, dataColumn);
        this.saveOrUpdate(dataColumn);
        return dataColumn;
    }

    @Override
    public void delete(DeleteBO deleteBO) {
        dataColumnMapper.deleteById(deleteBO.getId());
    }

}
