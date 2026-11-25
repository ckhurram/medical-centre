import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientRegistrationE2ETest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        // Playwright will use installed browsers
    }

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
    void patientRegistrationEndToEnd() {
        page.goto("http://localhost:3000/register");

        page.fill("input[name='email']", "testpatient@example.com");
        page.fill("input[name='firstName']", "John");
        page.fill("input[name='lastName']", "Doe");
        page.fill("input[name='dateOfBirth']", "1990-05-15");
        page.fill("input[name='phone']", "+1234567890");

        page.click("button[type='submit']");

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
        assertThat(page.locator(".success-message")).containsText("Registration successful");
    }

    @Test
    @Order(2)
    void patientLogin() {
        page.goto("http://localhost:3000/login");

        page.fill("input[name='email']", "testpatient@example.com");
        page.fill("input[name='password']", "password123");

        page.click("button[type='submit']");

        page.waitForSelector(".dashboard");
        assertThat(page.locator(".dashboard")).isVisible();
    }

    @Test
    @Order(3)
    void patientBookAppointment() {
        // Login first
        page.goto("http://localhost:3000/login");
        page.fill("input[name='email']", "testpatient@example.com");
        page.fill("input[name='password']", "password123");
        page.click("button[type='submit']");
        page.waitForSelector(".dashboard");

        // Navigate to booking
        page.goto("http://localhost:3000/book-appointment");

        // Select medic
        page.selectOption("select[name='medicId']", "medic-1");
        page.fill("input[name='appointmentDateTime']", "2026-12-01T10:00");

        page.click("button[type='submit']");

        page.waitForSelector(".booking-confirmation");
        assertThat(page.locator(".booking-confirmation")).isVisible();
    }
}