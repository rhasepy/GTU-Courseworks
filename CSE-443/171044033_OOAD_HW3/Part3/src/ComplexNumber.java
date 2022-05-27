import java.util.Random;

/**
 * My Complex number class
 */
public class ComplexNumber
{
    /**
     * real num
     */
    private double _real;

    /**
     * img num
     */
    private double _img;

    /**
     * consturcot
     */
    public ComplexNumber() {
        Random r = new Random();
        this._real = r.nextDouble();
        this._img = r.nextDouble();
    }

    /**
     * consturctor
     * @param _real real num
     * @param _img img num
     */
    public ComplexNumber(double _real, double _img) {
        this._real = _real;
        this._img = _img;
    }

    /**
     * setter
     * @param _real real num
     */
    public void set_real(double _real) {
        this._real = _real;
    }

    /**
     * setter
     * @param _img img num
     */
    public void set_img(double _img) {
        this._img = _img;
    }

    /**
     * getter
     * @return real num
     */
    public double get_real() {
        return _real;
    }

    /**
     * getter
     * @return img num
     */
    public double get_img() {
        return _img;
    }

    /**
     * sum operator
     * @param cnumber other complex number
     * @return this complex number + other complex number
     */
    public ComplexNumber sumComp(ComplexNumber cnumber) {
        return new ComplexNumber(cnumber._real, cnumber._img);
    }

    /**
     * to string
     * @return represent string from object
     */
    @Override
    public String toString() {
        if (_img > 0) {
            return get_real() + " + " + Math.abs(get_img()) + "i";
        }
        if (_img < 0) {
            return get_real() + " - " + Math.abs(get_img()) + "i";
        }
        if (_real == 0) {
            return get_img() + "i";
        }
        if (_img == 0) {
            return get_real() + "";
        }
        return null;
    }
}
