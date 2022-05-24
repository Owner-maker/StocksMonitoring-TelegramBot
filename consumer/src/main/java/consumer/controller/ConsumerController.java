package consumer.controller;

import consumer.models.Offset;
import consumer.repository.OffsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private OffsetRepository offsetRepository;

    @GetMapping("/getoffset")
    public Offset getOffset(){
        return offsetRepository.findByUserIdAndTopicName(1L,"test").get();
    }
}
