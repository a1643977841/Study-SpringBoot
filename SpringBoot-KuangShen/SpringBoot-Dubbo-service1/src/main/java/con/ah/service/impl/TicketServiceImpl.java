package con.ah.service.impl;

import con.ah.service.TicketService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/2113:49
 */
@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "ahaos";
    }
}
