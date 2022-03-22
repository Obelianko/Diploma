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

public class NegativeTest {

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

    @DisplayName("3 By Card with blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyForm() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getEmptyCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("4 By Credit with blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyForm() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var emptyCardInformation = DataHelper.getEmptyCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(emptyCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("5 By Card with Blank card data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var fieldCardEmpty = DataHelper.getFieldCardEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldCardEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("6 By Credit with Blank card details.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var fieldCardEmpty = DataHelper.getFieldCardEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldCardEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("7 By Card with Year field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var fieldYearEmpty = DataHelper.getFieldYearEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldYearEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("8 By Credit with Year field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var fieldYearEmpty = DataHelper.getFieldYearEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldYearEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("9 By Card with Month field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var fieldMonthEmpty = DataHelper.getFieldMonthEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldMonthEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("10 By Credit with Month field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var fieldMonthEmpty = DataHelper.getFieldMonthEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldMonthEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("11 By Card with Holder field blank data")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldHolder() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var fieldHolderEmpty = DataHelper.getFieldHolderEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldHolderEmpty);
        payByCard.requiredPayCardToFillIn();
    }

    @DisplayName("12 By Credit with Holder field blank data")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldHolder() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var fieldHolderEmpty = DataHelper.getFieldHolderEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldHolderEmpty);
        payByCreditCard.requiredCreditCardToFillIn();
    }

    @DisplayName("13 By Card with blank CVV field data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldCvv() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var fieldCvvEmpty = DataHelper.getFieldCVVEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldCvvEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("14 By Credit with blank CVV field data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCvv() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var fieldCvvEmpty = DataHelper.getFieldCVVEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldCvvEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("15 By Card Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var declinedCardInformation = DataHelper.getDeclinedCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(declinedCardInformation);
        payByCard.notSuccessfulPayCardPayment();

        var paymentId = SqlUtils.getPaymentId();
        var statusForPayment = SqlUtils.getStatusForPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("16 By Credit Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCreditCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var declinedCardInformation = DataHelper.getDeclinedCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(declinedCardInformation);
        payByCreditCard.notSuccessfulCreditCardPayment();

        var paymentId = SqlUtils.getPaymentId();
        var statusForPayment = SqlUtils.getStatusForCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("17 By card expired card (in previous years)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredYearCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardYear();
    }

    @DisplayName("18 By credit expired card (in previous years)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredYearCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardYear();
    }

    @DisplayName("19 By card expired card (in the last month)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardMonth();
    }

    @DisplayName("20 By Credit expired card (in the last month)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardMonth();
    }

    @DisplayName("21 By card that expires in the current month and year.")
    @Test
    public void shouldConfirmPaymentWithNowCurrentMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithCurrentMonthAndYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.successfulPayCardPayment();
    }

    @DisplayName("22 By credit with credit card that expires in the current month and year")
    @Test
    public void shouldConfirmBuyingOnCreditWithCurrentMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithCurrentMonthAndYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.successfulCreditCardPayment();
    }

    @DisplayName("23 By card that expires next month")
    @Test
    public void shouldConfirmPaymentWithNextMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithNextMonth();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.successfulPayCardPayment();
    }

    @DisplayName("24 By Credit with card that expires next month.")
    @Test
    public void shouldConfirmBuyingOnCreditWithNextMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithNextMonth();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.successfulCreditCardPayment();
    }

    @DisplayName("25 By card with zeros in the month and year field.")
    @Test
    public void shouldNotConfirmPaymentWithZeroMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroMonthAndYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardYear();
    }

    @DisplayName("26 By credit with card with zeros in the month and year field")
    @Test
    public void shouldConfirmBuyingOnCreditWithZeroMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroMonthAndYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardYear();
    }

    @DisplayName("27 By card with an incorrect date field format")
    @Test
    public void shouldNotConfirmPaymentWithWrongFormatFieldsCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("28 By credit with card with an incorrect date field format")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithWrongFormatFieldsCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("29 By card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmPaymentWithCyrillicHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("30 By Credit. Card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithCyrillicHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("31 By card with the holder's name in Special Symbol.")
    @Test
    public void shouldNotConfirmPaymentWithSpecialSymbolInHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("32 By credit. Card with the holder's name Special Symbol")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEnglishPlusNumbersPlusSpecialSymbolInHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("33 By card with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmPaymentWithNumericHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("34 By Credit. Card data with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithNumericHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("35 By card with Zero Format CVV")
    @Test
    public void shouldNotConfirmPaymentWithZeroFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroFormatCVV();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("36 By credit. Card with Zero Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithZeroFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroFormatCVV();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("37 By card with Invalid Format CVV.")
    @Test
    public void shouldNotConfirmPaymentWithInvalidFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = new PayByCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("38 By Credit. Card with Invalid Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithInvalidFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = new PayByCreditCardPage();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }
}