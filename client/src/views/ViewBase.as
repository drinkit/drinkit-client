/**
 * Created by Crabar on 26.10.2014.
 */
package views
{
    import spark.components.BorderContainer;

    [Event(name="viewChanged", type="models.events.ViewEvent")]
    public class ViewBase extends BorderContainer
    {
        public function ViewBase()
        {
            super();
            minHeight = 500;
        }
    }
}
