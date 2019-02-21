package com.sofyun.admin.service;

import com.sofyun.admin.domain.DataBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.database.InitBO;
import com.sofyun.admin.domain.request.database.SaveBO;
import com.sofyun.core.exception.BaseException;

/**
 * <p>
 * 数据库 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
public interface DataBaseService extends IService<DataBase> {

    /**
     * 保存数据
     * @param saveBO
     * @return
     */
    DataBase insert(SaveBO saveBO);

    void init(InitBO initBO) throws BaseException;

}
