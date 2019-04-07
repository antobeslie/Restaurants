package com.anto.restaurants.RestaurantsP.Models;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Restaurant_ {
    @SerializedName("R")
    @Expose
    private ResId r;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("switch_to_order_menu")
    @Expose
    private Integer switchToOrderMenu;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("average_cost_for_two")
    @Expose
    private Integer averageCostForTwo;
    @SerializedName("price_range")
    @Expose
    private Integer priceRange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("offers")
    @Expose
    private List<Object> offers = null;
    @SerializedName("opentable_support")
    @Expose
    private Integer opentableSupport;
    @SerializedName("is_zomato_book_res")
    @Expose
    private Integer isZomatoBookRes;
    @SerializedName("mezzo_provider")
    @Expose
    private String mezzoProvider;
    @SerializedName("is_book_form_web_view")
    @Expose
    private Integer isBookFormWebView;
    @SerializedName("book_form_web_view_url")
    @Expose
    private String bookFormWebViewUrl;
    @SerializedName("book_again_url")
    @Expose
    private String bookAgainUrl;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("photos_url")
    @Expose
    private String photosUrl;
    @SerializedName("menu_url")
    @Expose
    private String menuUrl;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("has_online_delivery")
    @Expose
    private Integer hasOnlineDelivery;
    @SerializedName("is_delivering_now")
    @Expose
    private Integer isDeliveringNow;
    @SerializedName("include_bogo_offers")
    @Expose
    private Boolean includeBogoOffers;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("is_table_reservation_supported")
    @Expose
    private Integer isTableReservationSupported;
    @SerializedName("has_table_booking")
    @Expose
    private Integer hasTableBooking;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("establishment_types")
    @Expose
    private List<Object> establishmentTypes = null;

    public ResId getR() {
        return r;
    }

    public void setR(ResId r) {
        this.r = r;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public void setSwitchToOrderMenu(Integer switchToOrderMenu) {
        this.switchToOrderMenu = switchToOrderMenu;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public Integer getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public Integer getOpentableSupport() {
        return opentableSupport;
    }

    public void setOpentableSupport(Integer opentableSupport) {
        this.opentableSupport = opentableSupport;
    }

    public Integer getIsZomatoBookRes() {
        return isZomatoBookRes;
    }

    public void setIsZomatoBookRes(Integer isZomatoBookRes) {
        this.isZomatoBookRes = isZomatoBookRes;
    }

    public String getMezzoProvider() {
        return mezzoProvider;
    }

    public void setMezzoProvider(String mezzoProvider) {
        this.mezzoProvider = mezzoProvider;
    }

    public Integer getIsBookFormWebView() {
        return isBookFormWebView;
    }

    public void setIsBookFormWebView(Integer isBookFormWebView) {
        this.isBookFormWebView = isBookFormWebView;
    }

    public String getBookFormWebViewUrl() {
        return bookFormWebViewUrl;
    }

    public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
        this.bookFormWebViewUrl = bookFormWebViewUrl;
    }

    public String getBookAgainUrl() {
        return bookAgainUrl;
    }

    public void setBookAgainUrl(String bookAgainUrl) {
        this.bookAgainUrl = bookAgainUrl;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public Integer getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(Integer hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public Integer getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(Integer isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public Boolean getIncludeBogoOffers() {
        return includeBogoOffers;
    }

    public void setIncludeBogoOffers(Boolean includeBogoOffers) {
        this.includeBogoOffers = includeBogoOffers;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public Integer getIsTableReservationSupported() {
        return isTableReservationSupported;
    }

    public void setIsTableReservationSupported(Integer isTableReservationSupported) {
        this.isTableReservationSupported = isTableReservationSupported;
    }

    public Integer getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(Integer hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public List<Object> getEstablishmentTypes() {
        return establishmentTypes;
    }

    public void setEstablishmentTypes(List<Object> establishmentTypes) {
        this.establishmentTypes = establishmentTypes;
    }

    @Override
    public String toString() {
        return "Restaurant_{" +
                "r=" + r +
                ", apikey='" + apikey + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", location=" + location +
                ", switchToOrderMenu=" + switchToOrderMenu +
                ", cuisines='" + cuisines + '\'' +
                ", averageCostForTwo=" + averageCostForTwo +
                ", priceRange=" + priceRange +
                ", currency='" + currency + '\'' +
                ", offers=" + offers +
                ", opentableSupport=" + opentableSupport +
                ", isZomatoBookRes=" + isZomatoBookRes +
                ", mezzoProvider='" + mezzoProvider + '\'' +
                ", isBookFormWebView=" + isBookFormWebView +
                ", bookFormWebViewUrl='" + bookFormWebViewUrl + '\'' +
                ", bookAgainUrl='" + bookAgainUrl + '\'' +
                ", thumb='" + thumb + '\'' +
                ", userRating=" + userRating +
                ", photosUrl='" + photosUrl + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", featuredImage='" + featuredImage + '\'' +
                ", hasOnlineDelivery=" + hasOnlineDelivery +
                ", isDeliveringNow=" + isDeliveringNow +
                ", includeBogoOffers=" + includeBogoOffers +
                ", deeplink='" + deeplink + '\'' +
                ", isTableReservationSupported=" + isTableReservationSupported +
                ", hasTableBooking=" + hasTableBooking +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", establishmentTypes=" + establishmentTypes +
                '}';
    }

    public static DiffUtil.ItemCallback<Restaurant_> DIFF_CALLBACK = new DiffUtil.ItemCallback<Restaurant_>() {
        @Override
        public boolean areItemsTheSame(@NonNull Restaurant_ restaurant_, @NonNull Restaurant_ t1) {
            return restaurant_.id.equals(t1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Restaurant_ restaurant_, @NonNull Restaurant_ t1) {
            return restaurant_.equals(t1);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Restaurant_ article = (Restaurant_) obj;
        return article.id == this.id;
    }

}
