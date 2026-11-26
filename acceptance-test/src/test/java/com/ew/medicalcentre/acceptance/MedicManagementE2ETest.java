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
        page.navigate("http://localhost:3000/admin/login");
        page.locator("input[name='email']").fill("admin@example.com");
        page.locator("input[name='password']").fill("admin123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".admin-dashboard");

        page.navigate("http://localhost:3000/admin/medics/new");

        page.locator("input[name='firstName']").fill("Jane");
        page.locator("input[name='lastName']").fill("Smith");
        page.locator("input[name='email']").fill("jane.smith@hospital.com");
        page.locator("input[name='specialization']").fill("Cardiology");
        page.locator("input[name='qualification']").fill("MD, FACC");

        page.locator("button[type='submit']").click();

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
    }

    @Test
    @Order(2)
    void adminSetMedicAvailability() {
        page.navigate("http://localhost:3000/admin/login");
        page.locator("input[name='email']").fill("admin@example.com");
        page.locator("input[name='password']").fill("admin123");
        page.locator("button[type='submit']").click();
        page.waitForSelector(".admin-dashboard");

        page.navigate("http://localhost:3000/admin/medics/medic-1/availability");

        page.locator("select[name='day']").selectOption("MONDAY");
        page.locator("input[name='startTime']").fill("09:00");
        page.locator("input[name='endTime']").fill("17:00");

        page.locator("button[type='submit']").click();

        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message")).isVisible();
    }
}