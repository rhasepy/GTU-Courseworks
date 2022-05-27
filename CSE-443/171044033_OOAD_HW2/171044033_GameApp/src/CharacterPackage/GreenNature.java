package CharacterPackage;

/**
 * Green Store class
 */
public class GreenNature extends CharacterStore
{
    /**
     * Create and return GameCharacter class
     * @param style, character style (atlantis, valhalla, underwild etc.)
     * @return GameCharacter
     */
    @Override
    public GameCharacter buildGameCharacter(CharacterStyle style) {
        if (style == CharacterStyle.ATLANTIS) {
            return new Atlantis(new GreenNatureFactory());
        } else if (style == CharacterStyle.VALHALLA) {
            return new Valhalla(new GreenNatureFactory());
        } else if (style == CharacterStyle.UNDERWILD) {
            return new Underwild(new GreenNatureFactory());
        } else {
            return null;
        }
    }
}
