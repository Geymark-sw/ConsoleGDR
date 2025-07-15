package theRiseOfITS.mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.concreto.entity.Merchant;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.concreto.entity.Player;
import theRiseOfITS.concreto.rooms.BossRoom;
import theRiseOfITS.concreto.rooms.MerchantRoom;

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
        if(nemici.getFirst() instanceof Boss boss) {
        	boss.speak();
        }

        while (!player.isDead() && !nemici.isEmpty()) {
            turnoGiocatore();
            if (nemici.isEmpty()) break; 

            turnoNemici();
            if (player.isDead()) break; 
        }

        fineCombattimento();
    }

    private void turnoGiocatore() {
        System.out.println("\n--- TURNO DI " + player.getName() + " ---");
        System.out.println(player);
        System.out.println();
        System.out.println("Nemici:");
        for (int i = 0; i < nemici.size(); i++) {
            System.out.println((i + 1) + ": " + nemici.get(i));
        }

        String comando = "";
        boolean comandoValido = false;

        while (!comandoValido) {
            System.out.println("\nCosa vuoi fare? (scrivi 'atk', 'heal' o 'inv')");
            System.out.print("Comando: ");
            comando = scanner.nextLine().trim().toLowerCase(); 

            if (comando.equals("atk") || comando.equals("heal") || comando.equals("inv")) {
                comandoValido = true;
            } else {
                System.out.println("Comando non valido. Inserisci 'atk', 'heal' o 'inv'.");
            }
        }

        if (comando.equals("atk")) { 
            // Mostra la lista dei nemici che il giocatore può attaccare
            System.out.println("Scegli un nemico da attaccare:");
            for (int i = 0; i < nemici.size(); i++) {
                System.out.println((i + 1) + ": " + nemici.get(i));
            }

            int sceltaNemico = -1;
            // Chiedi un input valido
            while (sceltaNemico < 1 || sceltaNemico > nemici.size()) {
                System.out.print("Inserisci il numero del nemico: ");
                try {
                    sceltaNemico = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero.");
                }
            }

            Entity nemicoScelto = nemici.get(sceltaNemico - 1);
            player.attack(nemicoScelto);

            // Se il nemico è morto, rimuovilo dalla lista
            if (nemicoScelto.isDead()) {
                System.out.println(nemicoScelto.getName() + " è stato sconfitto!");
                nemicoScelto.setHp(0); 
                dropLoot(nemicoScelto);
                nemici.remove(nemicoScelto);
            }
        } else if (comando.equals("heal")) { 
            player.heal(); 
        } else if (comando.equals("inv")) {
            // Entra nel menu inventario per una singola azione
            player.openCombatInventoryMenu(); // Nuovo metodo nel Player
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

            // Rimuove i mob e gli entity sconfitti
            if (player.getCurrentRoom() != null) {
                player.getCurrentRoom().rimuoviMobSconfitti();
            }
            
            

            // Se è una BossRoom e il boss è morto, rimuovilo
            if (player.getCurrentRoom() instanceof BossRoom bossRoom) {
                if (bossRoom.getBoss() != null && bossRoom.getBoss().isDead()) {
                    System.out.println("🏆 Hai sconfitto il Boss di questo livello!");
                    bossRoom.rimuoviBossSconfitti();
                }
            }
            // Se è una MerchantRoom e il mercante è morto, rimuovilo
            if (player.getCurrentRoom() instanceof MerchantRoom merchantRoom) {
            	if (merchantRoom.getMerchant() != null && merchantRoom.getMerchant().isDead()) {
            		System.out.println("Hai sconfitto il mercante!");
            		merchantRoom.rimuoviEntitySconfitte();
            	}
            }

        } else {
            System.out.println("SCONFITTA! Sei stato battuto.");
        }
    }
    
    private void dropLoot(Entity nemico) {
        List<Item> loot = new ArrayList<>();

        if (nemico instanceof Boss boss) {
            loot = boss.getDropsList();
        } else if (nemico instanceof Mob mob) {
            loot = mob.getListDrop();
            
        } else if (nemico instanceof Merchant merchant) {
        	loot = merchant.getInventario();
        }
        

        if (!loot.isEmpty() && player.getCurrentRoom() != null) {
        	player.getCurrentRoom().getItems().addAll(loot);
            System.out.println(nemico.getName() + " ha droppato:");
            for (Item item : loot) {
                System.out.println(" - " + item.getNome());
            }
        }
    }
}