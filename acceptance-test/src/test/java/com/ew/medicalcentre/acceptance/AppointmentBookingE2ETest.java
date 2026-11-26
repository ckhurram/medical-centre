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
        page.navigate("http://localhost:3000/login");
        page.locator("input[name='email']").fill("patient@example.com");
        page.locator("input[name='password']").fill("password123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".dashboard");

        page.navigate("http://localhost:3000/book-appointment");

        page.locator("select[name='medicId']").selectOption("medic-1");
        page.locator("input[name='appointmentDateTime']").fill("2026-12-01T10:00");
        page.locator("button[type='submit']").click();

        page.waitForSelector(".booking-confirmation");
        assertThat(page.locator(".booking-confirmation")).isVisible();
    }

    @Test
    @Order(2)
    void viewPatientAppointments() {
        page.navigate("http://localhost:3000/login");
        page.locator("input[name='email']").fill("patient@example.com");
        page.locator("input[name='password']").fill("password123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".dashboard");

        page.navigate("http://localhost:3000/appointments");

        page.waitForSelector(".appointment-list");
        assertThat(page.locator(".appointment-list")).isVisible();
        assertThat(page.locator(".appointment-item")).hasCount(1);
    }

    @Test
    @Order(3)
    void cancelAppointment() {
        page.navigate("http://localhost:3000/login");
        page.locator("input[name='email']").fill("patient@example.com");
        page.locator("input[name='password']").fill("password123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".dashboard");

        page.navigate("http://localhost:3000/appointments");
        page.waitForSelector(".appointment-list");

        page.locator(".cancel-button").click();
        page.waitForSelector(".cancellation-confirmation");
        assertThat(page.locator(".cancellation-confirmation")).isVisible();
    }
}