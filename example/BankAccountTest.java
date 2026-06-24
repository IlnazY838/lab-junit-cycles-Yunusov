import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeAll
    static void initAll() {
        System.out.println("🔧 Запуск всех тестов для BankAccount. Инициализация общих ресурсов...");
    }

    @BeforeEach
    void setUp() {
        // Создаем НОВЫЙ счет перед КАЖДЫМ тестом
        account = new BankAccount("Иван Петров", 1000.0);
        System.out.println("  ✅ Создан новый счет для теста. Баланс: 1000.0");
    }

    @AfterEach
    void tearDown() {
        // "Обнуляем" счет или просто логируем завершение теста
        System.out.println("  🧹 Тест завершен. Очистка не требуется, объект будет удален сборщиком мусора.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("🏁 Все тесты BankAccount завершены. Освобождение общих ресурсов.");
    }

    @Test
    @DisplayName("Тест: Пополнение счета увеличивает баланс 📈")
    void testDeposit() {
        double balanceBefore = account.getBalance();
        account.deposit(500.0);
        assertEquals(balanceBefore + 500.0, account.getBalance(), 0.001);
        System.out.println("    ⚔️ Выполняется testDeposit");
    }

    @Test
    @DisplayName("Тест: Снятие средств уменьшает баланс 📉")
    void testWithdrawSuccess() {
        account.withdraw(300.0);
        assertEquals(700.0, account.getBalance(), 0.001);
        System.out.println("    ⚔️ Выполняется testWithdrawSuccess");
    }

    @Test
    @DisplayName("Тест: Снятие средств больше баланса невозможно 🚫")
    void testWithdrawFail() {
        boolean result = account.withdraw(1500.0);
        assertFalse(result);
        assertEquals(1000.0, account.getBalance(), "Баланс не должен измениться");
        System.out.println("    ⚔️ Выполняется testWithdrawFail");
    }
}