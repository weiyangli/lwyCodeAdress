export default class DemoDao {
    /**
     * 
     */
    static toHelloPage(callback) {
        $.rest.get({url: Urls.PAGE_DEMO_REST,
success: (result) => {
                if (result.success) {
                    const time = result.data
                    callback(time)
                } else {
                    callback(time)
                }
            }
        })
    }
}
