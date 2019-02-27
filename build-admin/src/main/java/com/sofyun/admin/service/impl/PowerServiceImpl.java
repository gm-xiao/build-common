package com.sofyun.admin.service.impl;

import com.sofyun.admin.domain.Power;
import com.sofyun.admin.domain.request.power.SaveBO;
import com.sofyun.admin.mapper.PowerMapper;
import com.sofyun.admin.service.PowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.util.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-26
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements PowerService {

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private IdUtils idUtils;

    @Override
    public Power insert(SaveBO saveBO) {
        Power power = new Power();
        BeanUtils.copyProperties(saveBO, power);
        power.setId(idUtils.create());
        this.save(power);
        return power;
    }

    @Override
    public Power update(Power power) {
        this.saveOrUpdate(power);
        return power;
    }

    @Override
    public void delete(String id) {
        powerMapper.deleteById(id);
    }


}
