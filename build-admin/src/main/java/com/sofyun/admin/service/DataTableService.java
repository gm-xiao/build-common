package com.sofyun.admin.service;

import com.sofyun.admin.domain.DataTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.QueryBO;
import com.sofyun.admin.domain.request.datatable.SaveBO;
import com.sofyun.admin.domain.request.datatable.UpdateBO;

import java.util.List;

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

    DataTable update(UpdateBO updateBO);

    void delete(DeleteBO deleteBO);

    List<DataTable> list(DataTable dataTable);

    List<DataTable> list(QueryBO queryBO);
}
