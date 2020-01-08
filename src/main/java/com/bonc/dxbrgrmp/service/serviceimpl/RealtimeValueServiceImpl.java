package com.bonc.dxbrgrmp.service.serviceimpl;

import com.bonc.dxbrgrmp.dao.mybatis.mapper.RealtimeValueMapper;
import com.bonc.dxbrgrmp.service.IRealtimeValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RealtimeValueServiceImpl implements IRealtimeValueService {
    @Resource
    RealtimeValueMapper realtimeValueMapper;
    public long getNoneDateTimes(){
        return realtimeValueMapper.getNoneDateTimes();
    }
}
