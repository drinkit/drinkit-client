package models
{
    import flash.events.EventDispatcher;

    import models.supportClasses.ViewInformation;

    public class MainModel extends EventDispatcher
    {
        public static const BUILDER_VIEW:ViewInformation = new ViewInformation(0, "Конструктор коктейлей");
        public static const COCKTAIL_VIEW:ViewInformation = new ViewInformation(1, "Коктейль");
        public static const MY_BAR_VIEW:ViewInformation = new ViewInformation(2, "Мой бар");
        public static const ADMIN_VIEW:ViewInformation = new ViewInformation(3, "Админ-панель");

        public static const VIEWS:Object = {0: BUILDER_VIEW, 1: COCKTAIL_VIEW, 2: MY_BAR_VIEW, 3: ADMIN_VIEW};

        public function MainModel()
        {
            super(null);
        }
        [Bindable]
        public var currentView:ViewInformation = BUILDER_VIEW;
        public var viewData:Object;
    }
}
