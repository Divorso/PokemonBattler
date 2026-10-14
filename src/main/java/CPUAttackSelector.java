import java.util.ArrayList;
public class CPUAttackSelector implements AttackSelector {

    @Override
    public Attack chooseAttack(Pokemon pokemon){
        ArrayList<Attack> attacks = pokemon.getAttacks();

        int randomIndex = (int) (Math.random() * attacks.size());

        return attacks.get(randomIndex);
    }
}
