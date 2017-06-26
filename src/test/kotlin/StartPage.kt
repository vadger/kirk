import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 26.06.17.
 */
class StartPage : Page() {

    override val url: String?
        get() = "file:///home/sergey/Github/kirk/src/test/resources/start_page.html"

    fun fillForm(text: String) {
        element("#input").setVal(text)
    }
}