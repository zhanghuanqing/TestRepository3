package mains;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.jct.useThymeleaf.InMemoryMessageRepository;
import jp.co.jct.useThymeleaf.Message;
import jp.co.jct.useThymeleaf.MessageRepository;

@Controller
@SpringBootApplication
public class UseThymeleafApplication {

    // 在Spring 容器中加入内存管理消息实例
    @Bean
    public MessageRepository messageRepository() {
        return new InMemoryMessageRepository();
    }

    // 自定义类型转换，Controller 入参字符串转换为 Message 类型
    @Bean
    public Converter<String, Message> messageConverter() {
        return new Converter<String, Message>() {
            @Override
            public Message convert(String id) {
                return messageRepository().findMessage(Long.valueOf(id));
            }
        };
    }

//    // 跳转到消息列表
//    @GetMapping("/")
//    public ModelAndView index(){
//        return new ModelAndView("redirect:/useThymeleaf");
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UseThymeleafApplication.class, args);
    }
}