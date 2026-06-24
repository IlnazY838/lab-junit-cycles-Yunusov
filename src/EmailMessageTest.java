import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmailMessageTest {

    private EmailMessage email;

    @BeforeAll
    static void initAll() {
        System.out.println("🚀 Запуск всех тестов для EmailMessage");
        EmailMessage.resetTotalEmailsSent();
        System.out.println("   Счетчик отправленных писем сброшен: " + EmailMessage.getTotalEmailsSent());
        System.out.println();
    }

    @BeforeEach
    void setUp() {
        email = new EmailMessage("ivan@mail.ru", "petr@mail.ru");
        System.out.println("  ✅ Создано новое письмо от " + email.getSender() + " к " + email.getRecipient());
    }

    @AfterEach
    void tearDown() {
        System.out.println("  🧹 Тест завершен. Текущий счетчик писем: " + EmailMessage.getTotalEmailsSent());
        System.out.println();
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("🏁 Все тесты EmailMessage завершены");
        System.out.println("   📊 ИТОГО отправлено писем: " + EmailMessage.getTotalEmailsSent());
    }

    //Задание 1:
    @Test
    @DisplayName("Задание 1: Проверка начального состояния письма")
    void testInitialState() {
        System.out.println("    ⚔️ testInitialState");

        assertFalse(email.isSent(), "Новое письмо не должно быть отправлено");

        assertEquals("ivan@mail.ru", email.getSender(), "Отправитель должен совпадать");
        assertEquals("petr@mail.ru", email.getRecipient(), "Получатель должен совпадать");

        assertEquals("", email.getSubject(), "Тема должна быть пустой");
        assertEquals("", email.getBody(), "Тело должно быть пустым");

        System.out.println("    ✅ Письмо создано корректно, не отправлено");
    }

    //Задание 2:
    @Test
    @DisplayName("Задание 2: Проверка отправки письма (успешная и неуспешная)")
    void testSendEmail() {
        System.out.println("    ⚔️ testSendEmail");

        //Часть 1:
        email.setSubject("Встреча");
        email.setBody("Привет! Встречаемся завтра в 10:00.");

        boolean result = email.send();

        assertTrue(result, "Отправка должна быть успешной");
        assertTrue(email.isSent(), "Письмо должно быть отмечено как отправленное");
        assertEquals("Встреча", email.getSubject(), "Тема должна сохраниться");
        assertEquals("Привет! Встречаемся завтра в 10:00.", email.getBody(), "Тело должно сохраниться");

        System.out.println("    ✅ Успешная отправка письма");

        //Часть 2:
        EmailMessage emptySenderEmail = new EmailMessage("", "petr@mail.ru");
        emptySenderEmail.setSubject("Тест");
        emptySenderEmail.setBody("Тестовое сообщение");

        boolean result2 = emptySenderEmail.send();

        assertFalse(result2, "Отправка должна быть неуспешной");
        assertFalse(emptySenderEmail.isSent(), "Письмо не должно быть отправлено");

        System.out.println("    ✅ Неуспешная отправка (пустой отправитель)");
    }

    //Задание 3:
    @Test
    @DisplayName("Задание 3: Проверка статического счетчика totalEmailsSent")
    void testTotalEmailsCounter() {
        System.out.println("    ⚔️ testTotalEmailsCounter");

        int before = EmailMessage.getTotalEmailsSent();

        email.setSubject("Письмо 1");
        email.setBody("Тело 1");
        email.send();

        EmailMessage email2 = new EmailMessage("anna@mail.ru", "bob@mail.ru");
        email2.setSubject("Письмо 2");
        email2.setBody("Тело 2");
        email2.send();

        int after = EmailMessage.getTotalEmailsSent();
        assertEquals(before + 2, after, "Счетчик должен увеличиться на 2");

        EmailMessage invalidEmail = new EmailMessage("", "");
        invalidEmail.setSubject("Тест");
        invalidEmail.setBody("Тело");
        invalidEmail.send();

        int afterInvalid = EmailMessage.getTotalEmailsSent();
        assertEquals(after, afterInvalid, "Неуспешная отправка не должна увеличивать счетчик");

        System.out.println("    ✅ Счетчик увеличился корректно: " + before + " -> " + after);
        System.out.println("    ✅ Неуспешная отправка не увеличила счетчик: " + afterInvalid);
    }
}