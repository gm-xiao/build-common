package com.sofyun.admin.service;

import com.sofyun.admin.domain.DataColumn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.datacolumn.SaveBO;
import com.sofyun.admin.domain.request.datacolumn.UpdateBO;

/**
 * <p>
 * 数据表字段 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
public interface DataColumnService extends IService<DataColumn> {

    DataColumn insert(SaveBO saveBO);

    DataColumn update(UpdateBO updateBO);

    void delete(DeleteBO deleteBO);

}
