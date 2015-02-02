/**
 * Created by Crabar on 02.08.2014.
 */
package views.renderers
{
    import design.DesignModel;

    public class SelectedIngredientItemRenderer extends BaseIngredientItemRenderer
    {
        public function SelectedIngredientItemRenderer()
        {
            super();
            backgroundColor = DesignModel.ORANGE_BEACH;
        }
    }
}
