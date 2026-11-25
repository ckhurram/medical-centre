import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentBookingE2ETest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @AfterEach
    void teardown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    @Order(1)
    void bookAppointmentEndToEnd() {
        page.goto("http://localhost:3000/login");
        page.fill("input[name='email']", "patient@example.com");
        page.fill("input[name='password']", "password123");
        page.click("button[type='submit']");
        page.waitForSelector(".dashboard");

        page.goto("http://localhost:3000/book-appointment");

        page.selectOption("select[name='medicId']", "medic-1");
        page.fill("input[name='appointmentDateTime']", "2026-12-01T10:00");
        page.click("button[type='submit']");

        page.waitForSelector(".booking-confirmation");
        assertThat(page.locator(".booking-confirmation")).isVisible();
    }

    @Test
    @Order(2)
    void viewPatientAppointments() {
        page.goto("http://localhost:3000/login");
        page.fill("input[name='email']", "patient@example.com");
        page.fill("input[name='password']", "password123");
        page.click("button[type='submit']");
        page.waitForSelector(".dashboard");

        page.goto("http://localhost:3000/appointments");

        page.waitForSelector(".appointment-list");
        assertThat(page.locator(".appointment-list")).isVisible();
        assertThat(page.locator(".appointment-item")).hasCount(1);
    }

    @Test
    @Order(3)
    void cancelAppointment() {
        page.goto("http://localhost:3000/login");
        page.fill("input[name='email']", "patient@example.com");
        page.fill("input[name='password']", "password123");
        page.click("button[type='submit']");
        page.waitForSelector(".dashboard");

        page.goto("http://localhost:3000/appointments");
        page.waitForSelector(".appointment-list");

        page.click(".cancel-button");
        page.waitForSelector(".cancellation-confirmation");
        assertThat(page.locator(".cancellation-confirmation")).isVisible();
    }
}