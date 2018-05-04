package com.example.carrepair.widget;


/**
 * 常量
 */
public class Contants {

    public static final String USER_JSON = "user_json";
    public static final String TOKEN = "token";

    public static final int REQUEST_CODE = 0;

    public static class OIL {
        private static final String MOBIL_1 = "https://img1.tuhu.org//Images/Products/530d/ec18/01505cff98f6063d2fa1007f_w800_h800.jpg";
        private static final String MOBIL_DES = "矿物机油 5W-30 SN级维修服务";
        public static final float MOBIL_PRICE = 149.00f;
        private static final String MOBIL_2 = "https://img4.tuhu.org/Images/Products/1608/6318F8EB7E8E15AD.jpg@163w_163h_100Q.jpg";
        private static final String MOBIL_DES2 = "矿物机油 4W-30 SW级维修服务";
        private static final float MOBIL_2_PRICE = 329.00f;
        private static final String MOBIL_3 = "https://img3.tuhu.org/Images/Products/c48d/80fd/e6b8842d45c3674d8025fc07_w800_h800.jpg@163w_163h_100Q.jpg";
        private static final String MOBIL_DES3 = "全品机油 5W-30 SN级维修服务";


        public static String getOilUrl(int i) {
            switch (i) {
                case 0:
                    return MOBIL_1;
                case 1:
                    return MOBIL_2;
                case 2:
                    return MOBIL_3;
                default:
                    return MOBIL_1;
            }
        }


        public static String getOilDes(int i) {
            switch (i) {
                case 0:
                    return MOBIL_DES;
                case 1:
                    return MOBIL_DES2;
                case 2:
                    return MOBIL_DES3;
                default:
                    return MOBIL_DES;
            }
        }

        public static float getOilPrice(int i) {
            switch (i) {
                case 0:
                case 1:
                    return MOBIL_PRICE;
                case 2:
                    return MOBIL_2_PRICE;
                default:
                    return MOBIL_PRICE;

            }
        }

    }

    public static class FILTER {
        private static String filterUrl1 = "https://img1.tuhu.org//Images/Products/by/OC980.jpg";
        private static String filterName = "马勒机油滤清器修车技能服务";
        private static float filterPrice = 25.00f;
        private static String filterUrl2 = "https://img1.tuhu.org/Images/Products/1511/YBMJYL.6CB227D1.jpg@163w_163h_100Q.jpg";
        private static String filterName2 = "意奔玛机油滤清器修车服务";
        private static float filterPrice2 = 11.00f;
        private static String filterUrl3 = "https://img4.tuhu.org/Images/Products/83f0/ab39/8bc1e3758f013faea8d752ed_w800_h800.jpg@163w_163h_100Q.jpg";
        private static String filterName3 = "汉格斯特机油滤清器维修服务";
        private static float filterPrice3 = 19.00f;

        public static String getFilterUrl(int i) {
            switch (i) {
                case 0:
                    return filterUrl1;
                case 1:
                    return filterUrl2;
                case 2:
                    return filterUrl3;
                default:
                    return filterUrl1;
            }
        }

        public static String getFilterName(int i) {
            switch (i) {
                case 0:
                    return filterName;
                case 1:
                    return filterName2;
                case 3:
                    return filterName3;
                default:
                    return filterName;
            }
        }

        public static float getFilterPrice(int i) {
            switch (i) {
                case 0:
                    return filterPrice;
                case 1:
                    return filterPrice2;
                case 3:
                    return filterPrice3;
                default:
                    return filterPrice;
            }
        }
    }

    public static class NOTICE {
        private static String dayNo1 = "https://img2.tuhu.org/activity/image/b35b/caa7/89fd7507eefe449d5617d573_w580_h230.png";
        private static String titleNo1 = "买轮胎送胎压监测";
        private static String dayNo2 = "https://img2.tuhu.org/activity/image/7830/2c77/4ae7e11b3b270b62458b3aa6_w580_h230.png";
        private static String titleNo2 = "精选轮胎";
        private static String dayNo3 = "https://img2.tuhu.org/activity/image/5566/98b6/a0d47f34fb23024b1dfa405e_w580_h230.png";
        private static String titleNo3 = "买三送一";
        private static String dayNo4 = "https://img2.tuhu.org/activity/image/bebd/2eb3/4fe6b90d3d6caf0c94d5ac8c_w580_h230.png";
        private static String titleNo4 = "买三送一";
        private static String dayNo5 = "https://img2.tuhu.org/activity/image/4ccf/84e6/d3fe734b1ee6d8ccf4f5d43f_w580_h230.png";
        private static String titleNo5 = "买三送一";
        private static String dayNo6 = "https://img2.tuhu.org/activity/image/ccd1/4b09/14945ed09298ab663e5ae087_w580_h230.png";
        private static String titleNo6 = "买三送一";

        public static String getDay(int i) {
            switch (i) {
                case 0:
                    return dayNo1;
                case 1:
                    return dayNo2;
                case 2:
                    return dayNo3;
                case 3:
                    return dayNo4;
                case 4:
                    return dayNo5;
                case 6:
                    return dayNo6;
                default:
                    return dayNo1;
            }
        }
        public static String getTitle(int i){
            switch (i) {
                case 0:
                    return titleNo1;
                case 1:
                    return titleNo2;
                case 2:
                    return titleNo3;
                case 3:
                    return titleNo4;
                case 4:
                    return titleNo5;
                case 6:
                    return titleNo6;
                default:
                    return titleNo1;
            }
        }

    }
    public static class SLIDER {
        private static String sliderUrl1 = "https://img1.tuhu.org/Home/Image/579F320072C62962BE2C3274920D7939.png";
        private static String sliderUrl2 = "https://img1.tuhu.org/Home/Image/2F61F7B2D1E83F0094F31A279CA5BA6D.jpg";
        private static String sliderUrl3 = "https://img1.tuhu.org/Home/Image/2AFC383E74D85C3EC4D7D9D3ED39085E.png";
        private static String sliderUrl4 = "https://img1.tuhu.org/Home/Image/D76B4F5CA683C597E32B7D2F3B31A693.png";
        private static String sliderText0 = "固特异品牌日";
        private static String sliderText1 = "推轮捧新";
        private static String sliderText2 = "胎压检测";
        private static String sliderText3 = "远足野营，其乐无穷";

        public static String getUrl(int i) {
            switch (i) {
                case 0:
                    return sliderUrl1;
                case 1:
                    return sliderUrl2;
                case 2:
                    return sliderUrl3;
                case 3:
                    return sliderUrl4;
                default:
                    return sliderUrl1;
            }
        }
        public static String getText(int i){
            switch (i){
                case 0:
                    return sliderText0;
                case 1:
                    return sliderText1;
                case 2:
                    return sliderText2;
                case 3:
                    return sliderText3;
                default:
                    return sliderText0;
            }
        }
    }


}
