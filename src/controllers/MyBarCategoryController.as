/**
 * Created by Crabar on 29.06.2015.
 */
package controllers
{
    import models.MyBarCategoryModel;

    public class MyBarCategoryController
    {
        public function MyBarCategoryController(model:MyBarCategoryModel)
        {
            _model = model;
        }

        private var _model:MyBarCategoryModel;
    }
}
