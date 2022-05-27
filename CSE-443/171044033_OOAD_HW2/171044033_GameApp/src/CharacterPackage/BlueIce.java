package CharacterPackage;

/**
 * Blue Store factory class
 */
public class BlueIce extends CharacterStore
{
    /**
     * Create and return GameCharacter
     * @param style, style of character
     * @return GameCharacter
     */
    @Override
    public GameCharacter buildGameCharacter(CharacterStyle style) {
        if (style == CharacterStyle.ATLANTIS) {
            return new Atlantis(new BlueIceFactory());
        } else if (style == CharacterStyle.VALHALLA) {
            return new Valhalla(new BlueIceFactory());
        } else if (style == CharacterStyle.UNDERWILD) {
            return new Underwild(new BlueIceFactory());
        } else {
            return null;
        }
    }
}
