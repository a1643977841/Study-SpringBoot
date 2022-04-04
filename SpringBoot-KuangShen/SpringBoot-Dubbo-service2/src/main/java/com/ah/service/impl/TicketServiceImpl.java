package com.ah.service.impl;

import com.ah.service.TicketService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/2113:49
 */
@Service
@Component
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "ahaos";
    }
}
