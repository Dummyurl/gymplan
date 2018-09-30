package ir.blackd.twitter.model;

/**
 * Created by Diamond Android on 12/19/2016.
 */
public class Movie {
    private String title, genre, year,imgProduct,mainM,helpM;

    public Movie() {
    }

    public Movie(String title, String genre, String year,String mainM,String helpM, String imgProduct) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.mainM = mainM;
        this.helpM = helpM;
        this.imgProduct=imgProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getImage() {
        return imgProduct;
    }

    public void setImage(String imgProduct) {
        this.imgProduct = imgProduct;
    }


    public String getMian() {
        return mainM;
    }

    public void setMainM(String mainM) {
        this.mainM = mainM;
    }


    public String getHelp() {
        return helpM;
    }

    public void setHelp(String helpM) {
        this.helpM = helpM;
    }
}
