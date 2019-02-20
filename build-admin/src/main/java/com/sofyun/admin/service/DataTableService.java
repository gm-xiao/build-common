package com.sofyun.admin.service;

import com.sofyun.admin.domain.DataTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.datatable.SaveBO;

/**
 * <p>
 * 数据表 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
public interface DataTableService extends IService<DataTable> {

    DataTable insert(SaveBO saveBO);
}
