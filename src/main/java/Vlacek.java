import java.util.LinkedList;
import java.util.List;

public class Vlacek {

    private Vagonek lokomotiva = new Vagonek(VagonekType.LOKOMOTIVA);
    private Vagonek posledni = new Vagonek(VagonekType.POSTOVNI);
    private int delka = 2;

    public Vlacek(){
        lokomotiva.setNasledujici(posledni);
        lokomotiva.setUmisteni(1);
        posledni.setPredchozi(lokomotiva);
        posledni.setUmisteni(2);
    }

    /**
     * Přidávejte vagonky do vlaku
     * Podmínka je že vagonek první třídy musí být vždy řazen za předchozí vagonek toho typu, pokud žádný takový není je řazen rovnou za lokomotivu
     * vagonek 2 třídy musí být vždy řazen až za poslední vagonek třídy první
     * Poštovní vagonek musí být vždy poslední vagonek lokomotivy
     * Při vkládání vagonku nezapomeňte vagonku přiřadit danou pozici ve vlaku
     * !!!!!!! POZOR !!!!!! pokud přidáváte vagonek jinak než na konec vlaku musíte všem následujícím vagonkům zvýšit jejich umístění - doporučuji si pro tento účel vytvořit privátní metodu
     * @param type
     */
    public void pridatVagonek(VagonekType type) {
        Vagonek newVagonek = new Vagonek(type);
        switch (type) {
            case PRVNI_TRIDA:
                lokomotiva.getNasledujici().setPredchozi(newVagonek);
                lokomotiva.setNasledujici(newVagonek);
                for (int i = 1; i < delka; i++) {
                    newVagonek.setNasledujici(posledni);
                    newVagonek.setPredchozi(lokomotiva);
                    newVagonek.setUmisteni(i);
                }
                newVagonek.getNasledujici().setPredchozi(newVagonek);
                posledni.setPredchozi(newVagonek);
                posledni.setUmisteni(delka);
                break;
            case DRUHA_TRIDA:
                if (getDelkaByType(VagonekType.PRVNI_TRIDA) == 0) {
                    lokomotiva.setNasledujici(newVagonek);
                    for (int i = 1; i < delka; i++) {
                        newVagonek.setNasledujici(posledni);
                        newVagonek.setPredchozi(lokomotiva);
                        newVagonek.setUmisteni(i);
                    }
                } else if (getDelkaByType(VagonekType.PRVNI_TRIDA) > 0) {
                    Vagonek posledniFirstClass = getLastVagonekByType(VagonekType.PRVNI_TRIDA);
                    posledniFirstClass.getNasledujici().setPredchozi(newVagonek);
                    for (int i = 1; i < delka; i++) {
                        newVagonek.setNasledujici(posledni);
                        newVagonek.setPredchozi(lokomotiva);
                        newVagonek.setUmisteni(i);
                    }
                    posledni.setUmisteni(delka);
                }

                delka++;
                break;

        }
    }

    public Vagonek getVagonekByIndex(int index) {
        int i = 1;
        Vagonek atIndex = lokomotiva;
        while(i < index) {
            atIndex = atIndex.getNasledujici();
            i++;
        }
        return atIndex;
    }


    /**
     * Touto metodou si můžete vrátit poslední vagonek daného typu
     * Pokud tedy budu chtít vrátit vagonek typu lokomotiva dostanu hned první vagonek
     * @param type
     * @return
     */
    public Vagonek getLastVagonekByType(VagonekType type) {
        Vagonek newVagonek = new Vagonek(type);
        switch (type){
            case PRVNI_TRIDA:
                for (int i = 1; i < getDelkaByType(type); i++) {
                    newVagonek = getVagonekByIndex(i);
                }break;
            case DRUHA_TRIDA:
                for (int i = 1+getDelkaByType(VagonekType.PRVNI_TRIDA); i < getDelkaByType(type) ; i++) {
                    newVagonek = getVagonekByIndex(i);
                }break;
        }
        return null;
    }

    /**
     * Tato funkce přidá jídelní vagonek za poslední vagonek první třídy, pokud jídelní vagonek za vagonkem první třídy již existuje
     * tak se další vagonek přidá nejblíže středu vagonků druhé třídy
     * tzn: pokud budu mít č osobních vagonků tak zařadím jídelní vagonek za 2 osobní vagónek
     * pokud budu mít osobních vagonků 5 zařadím jídelní vagonek za 3 osobní vagonek
     */
    public void pridatJidelniVagonek() {
        Vagonek jidelni = new Vagonek(VagonekType.JIDELNI);
    }

    /**
     * Funkce vrátí počet vagonků daného typu
     * Dobré využití se najde v metodě @method(addJidelniVagonek)
     * @param type
     * @return
     */
    public int getDelkaByType(VagonekType type) {
        int delkaTypu = 0;
        for (int i = 1; i <= delka ; i++) {
            if (getVagonekByIndex(i).getType() == type){
                delkaTypu++;
            }
        }
        return delkaTypu;
    }

    /**
     * Hledejte jidelni vagonky
     * @return
     */
    public List<Vagonek> getJidelniVozy() {
        List<Vagonek> jidelniVozy = new LinkedList<>();

        return jidelniVozy;
    }

    /**
     * Odebere poslední vagonek daného typu
     * !!!! POZOR !!!!! pokud odebíráme z prostředku vlaku musíme zbývající vagonky projít a snížit jejich umístění ve vlaku
     * @param type
     */
    public void odebratPosledniVagonekByType(VagonekType type) {

    }

    public int getDelka() {
        return delka;
    }
}
