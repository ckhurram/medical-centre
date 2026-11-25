import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicManagementE2ETest {
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
    void adminRegisterMedic() {
        page.goto("http://localhost:3000/admin/login");
        page.fill("input[name='email']", "admin@example.com");
        page.fill("input[name='password']", "admin123");
        page.click("button[type='submit']");
        page.waitForSelector(".admin-dashboard");

        page.goto("http://localhost:3000/admin/medics/new");

        page.fill("input[name='firstName']", "Jane");
        page.fill("input[name='lastName']", "Smith");
        page.fill("input[name='email']", "jane.smith@hospital.com");
        page.fill("input[name='specialization']", "Cardiology");
        page.fill("input[name='qualification']", "MD, FACC");

        page.click("button[type='submit']");

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
    }

    @Test
    @Order(2)
    void adminSetMedicAvailability() {
        page.goto("http://localhost:3000/admin/login");
        page.fill("input[name='email']", "admin@example.com");
        page.fill("input[name='password']", "admin123");
        page.click("button[type='submit']");
        page.waitForSelector(".admin-dashboard");

        page.goto("http://localhost:3000/admin/medics/medic-1/availability");

        page.selectOption("select[name='day']", "MONDAY");
        page.fill("input[name='startTime']", "09:00");
        page.fill("input[name='endTime']", "17:00");

        page.click("button[type='submit']");

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
    }
}