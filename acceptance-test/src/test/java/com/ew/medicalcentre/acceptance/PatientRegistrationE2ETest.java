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
        page.navigate("http://localhost:3000/register");

        page.locator("input[name='email']").fill("testpatient@example.com");
        page.locator("input[name='firstName']").fill("John");
        page.locator("input[name='lastName']").fill("Doe");
        page.locator("input[name='dateOfBirth']").fill("1990-05-15");
        page.locator("input[name='phone']").fill("+1234567890");

        page.locator("button[type='submit']").click();

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
        assertThat(page.locator(".success-message")).containsText("Registration successful");
    }

    @Test
    @Order(2)
    void patientLogin() {
        page.navigate("http://localhost:3000/login");

        page.locator("input[name='email']").fill("testpatient@example.com");
        page.locator("input[name='password']").fill("password123");

        page.locator("button[type='submit']").click();

        page.waitForSelector(".dashboard");
        assertThat(page.locator(".dashboard")).isVisible();
    }

    @Test
    @Order(3)
    void patientBookAppointment() {
        // Login first
        page.navigate("http://localhost:3000/login");
        page.locator("input[name='email']").fill("testpatient@example.com");
        page.locator("input[name='password']").fill("password123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".dashboard");

        // Navigate to booking
        page.navigate("http://localhost:3000/book-appointment");

        page.locator("select[name='medicId']").selectOption("medic-1");
        page.locator("input[name='appointmentDateTime']").fill("2026-12-01T10:00");

        page.locator("button[type='submit']").click();

        page.waitForSelector(".booking-confirmation");
        assertThat(page.locator(".booking-confirmation")).isVisible();
    }
}