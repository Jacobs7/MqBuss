package com.dp.controller;

import com.dp.common.JSONUtil;
import com.dp.common.commonsex;
import com.dp.entity.TestEntity;
import com.dp.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManagerController {


    @Autowired
    private ProducerService producerService;

    @Autowired
    @Qualifier("queueDestination")
    private Destination queueDestination;

    @Autowired
    @Qualifier("topicDestination")
    private Destination topicDestination;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {

        TestEntity testEntity = new TestEntity();

        testEntity.setId(1);
        testEntity.setName("FirstMQQueue");
        testEntity.setSex(commonsex.MALE);

        List<TestEntity> list = new ArrayList();
        list.add(testEntity);

        Map<String,List<TestEntity>> map = new HashMap();

        map.put("user",list);



        producerService.sendMsg(topicDestination, JSONUtil.obj2StringPretty(list));



        return "/2233.jsp";
    }

}
