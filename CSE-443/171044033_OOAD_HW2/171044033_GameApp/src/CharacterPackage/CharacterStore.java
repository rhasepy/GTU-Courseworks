package CharacterPackage;

/**
 * abstract factory store class
 */
public abstract class CharacterStore
{
    /**
     * build and return character from factory
     * @param style, style of character
     * @return game character
     */
    public abstract GameCharacter buildGameCharacter(CharacterStyle style);
}
