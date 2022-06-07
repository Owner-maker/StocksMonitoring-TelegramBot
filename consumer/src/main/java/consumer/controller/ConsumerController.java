package consumer.controller;

import consumer.service.ConsumerData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private final ConsumerData consumerData;

    public ConsumerController(ConsumerData consumerData) {
        this.consumerData = consumerData;
    }

    @PostMapping("/addGroupId")
    public HttpStatus addGroupId(@RequestParam int groupId){
        consumerData.setGroupId(groupId);
        return HttpStatus.OK;
    }

    @PostMapping("/addLeaderGroupAddress")
    public HttpStatus addLeaderGroupAddress(@RequestParam String address){
        consumerData.setLeaderGroupAddress(address);
        return HttpStatus.OK;
    }

    @GetMapping("/isGroupLeader")
    public boolean isGroupLeader(){
        return consumerData.isLeaderGroup();
    }
}
