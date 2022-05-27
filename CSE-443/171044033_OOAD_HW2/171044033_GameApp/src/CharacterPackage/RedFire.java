package CharacterPackage;

/**
 * Red Store class
 */
public class RedFire extends CharacterStore
{
    /**
     * Create and return GameCharacter
     * @param style, Character style (atlantis valhalla or underwild)
     * @return return GameCharacter instance
     */
    @Override
    public GameCharacter buildGameCharacter(CharacterStyle style) {
        if (style == CharacterStyle.ATLANTIS) {
            return new Atlantis(new RedFireFactory());
        } else if (style == CharacterStyle.VALHALLA) {
            return new Valhalla(new RedFireFactory());
        } else if (style == CharacterStyle.UNDERWILD) {
            return new Underwild(new RedFireFactory());
        } else {
            return null;
        }
    }
}
