package theRiseOfITS.mechanics;

import java.util.List;
import java.util.Scanner;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.concreto.entity.Player;
import theRiseOfITS.concreto.rooms.BossRoom;

public class CombatSystem {
    private Player player;
    private List<Entity> nemici;
    private Scanner scanner;

    public CombatSystem(Player player, List<Entity> nemici) {
        this.player = player;
        this.nemici = nemici;
        this.scanner = new Scanner(System.in);
    }
    
    public CombatSystem() {
		// TODO Auto-generated constructor stub
	}
    

    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Entity> getNemici() {
		return nemici;
	}

	public void setNemici(List<Entity> nemici) {
		this.nemici = nemici;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void startCombat() {
        System.out.println("--- INIZIO COMBATTIMENTO ---");

        // Il ciclo continua finché il giocatore è vivo e ci sono ancora nemici
        while (!player.isDead() && !nemici.isEmpty()) {
            // 1. Turno del Giocatore
            turnoGiocatore();
            if (nemici.isEmpty()) break; // Se il giocatore ha sconfitto l'ultimo nemico

            // 2. Turno dei Nemici
            turnoNemici();
            if (player.isDead()) break; // Se un nemico ha sconfitto il giocatore
        }

        // 3. Fine del combattimento
        fineCombattimento();
        //scanner.close();
    }

    private void turnoGiocatore() {
        System.out.println("\n--- TURNO DI " + player.getName() + " ---");
        System.out.println(player); // Mostra lo stato del giocatore

        // Mostra la lista dei nemici che il giocatore può attaccare
        System.out.println("Scegli un nemico da attaccare:");
        for (int i = 0; i < nemici.size(); i++) {
            System.out.println((i + 1) + ": " + nemici.get(i));
        }

        int scelta = -1;
        // Chiedi un input valido
        while (scelta < 1 || scelta > nemici.size()) {
            System.out.print("Inserisci il numero del nemico: ");
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }

        Entity nemicoScelto = nemici.get(scelta - 1);
        player.attack(nemicoScelto);

        // Se il nemico è morto, rimuovilo dalla lista
        if (nemicoScelto.isDead()) {
            System.out.println(nemicoScelto.getName() + " è stato sconfitto!");
            nemicoScelto.setHp(0);
            nemici.remove(nemicoScelto);
        }
    }

    private void turnoNemici() {
        System.out.println("\n--- TURNO DEI NEMICI ---");
        // Ogni nemico vivo attacca il giocatore
        for (Entity nemico : nemici) {
            if (!nemico.isDead()) { // Controllo se il nemico è ancora vivo per poter attaccare
            	System.out.println(nemico.getName() + " infligge "+ (nemico.getAtk()-player.getDef()) + " danni a "+player.getName()+"!");
                nemico.attack(player);
                // Se il giocatore muore a metà del turno dei nemici, si esce subito
                if (player.isDead()) {
                    break;
                }
            }
        }
    }

    private void fineCombattimento() {
        System.out.println("\n--- FINE COMBATTIMENTO ---");
        if (!player.isDead()) {
            System.out.println("VITTORIA! Hai sconfitto tutti i nemici.");

            // Rimuove i mob normali sconfitti
            if (player.getCurrentRoom() != null) {
                player.getCurrentRoom().rimuoviMobSconfitti();
            }

            // Se è una BossRoom e il boss è morto, rimuovilo
            if (player.getCurrentRoom() instanceof BossRoom bossRoom) {
                if (bossRoom.getBoss() != null && bossRoom.getBoss().isDead()) {
                    System.out.println("🏆 Hai sconfitto il Boss di questo livello!");
                    bossRoom.rimuoviBossSconfitti();
                    // 🔁 qui potresti attivare un flag globale o notificare al GameManager che si può salire di livello
                }
            }

        } else {
            System.out.println("SCONFITTA! Sei stato battuto.");
        }
    }

}