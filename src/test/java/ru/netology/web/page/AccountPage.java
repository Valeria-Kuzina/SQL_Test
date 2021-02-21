package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {
    private SelenideElement account = $(byText("Личный кабинет"));

    public AccountPage() {
        account.waitUntil(Condition.visible, 15000);
    }
}