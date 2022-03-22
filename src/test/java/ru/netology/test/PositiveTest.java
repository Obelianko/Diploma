package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.SQLunits.SqlUtils;
import ru.netology.data.DataHelper;
import ru.netology.page.PayByCardPage;
import ru.netology.page.PayByCreditCardPage;
import ru.netology.page.TourOfferPage;

import static com.codeborne.selenide.Selenide.open;


public class PositiveTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }

    @DisplayName("1. Successful purchase by card.")
    @Test
    public void shouldConfirmPaymentWithApprovedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var approvedCardInformation = DataHelper.getValidCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(approvedCardInformation);
        payByCard.successfulPayCardPayment();

        var paymentId = SqlUtils.getPaymentId();
        var statusForPayment = SqlUtils.getStatusForPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }

    @DisplayName("2. Successful credit purchase")
    @Test
    public void shouldConfirmBuyingOnCreditWithApprovedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var approvedCardInformation = DataHelper.getValidCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(approvedCardInformation);
        payByCreditCard.successfulCreditCardPayment();

        var paymentId = SqlUtils.getPaymentId();
        var statusForPayment = SqlUtils.getStatusForCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }
}
