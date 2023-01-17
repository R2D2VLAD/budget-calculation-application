package com.example.budget.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {


    @GetMapping
    public String programms() {
        return "Приложение запущено!";
    }
    @GetMapping("/info")
    public String data() {
        return "Имя ученика: Владислав |||" +
                " Название проекта: Знакомство с сборщиком Maven |||" +
                " Дата создания проекта: 13.01.23 |||" +
                " Описание проекта: Проект создан для ознакомления с сборщиком Maven!";
    }
}
