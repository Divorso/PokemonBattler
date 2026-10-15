import java.util.Scanner;

public class Graphics implements GraphicsSelector{
        @Override
        public void getMessage(String message) {
            int width = Math.max(80, message.length() + 4);
            String border = "=".repeat(width);

            int leftPadding = (width - message.length() - 2) / 2;
            int rightPadding = width - message.length() - 2 - leftPadding;

            System.out.println(border);
            System.out.println("=" + " ".repeat(leftPadding)
                    + message
                    + " ".repeat(rightPadding) + "=");
            System.out.println(border);
        }
        @Override
        public void getPokedexIntro(String message) {
            System.out.println("""
                    █▀▀▀▀▀▀▄   ▄▀▀▀▀▀▄  █▀▀▀▄▀▀▀▄  ▄▀▀▀▀▀▀█ ▄▀▀▀▄ ▄▀▀▄   ▄▀▀▀▀▀▄  █▀▀▀▀▀▀▄\s
                    █  ▄▄▄  █ █   ▄▄  ▄ █   ▄   ▀ █   ▄▄▄▄█ █    ▀    ▄ █   ▄▄  ▄ █       ▄
                    █  ▀   ▄▀ █   ▀   █ █     ▀▄  █     ▄   █   ▄ ▄   █ █   ▀   █ █   ▄   █
                    █   █▀▀   █   ▀   █ █   █   █ █   ▀▀▀▀█ █  █ ▀ █  █ █   ▀   █ █  █    █
                    █▄▄▄█      ▀▄▄▄▄▄▀  █▄▄▄▀▄▄▄█  ▀▄▄▄▄▄▄█ █▄▄█   █▄▄█  ▀▄▄▄▄▄▀  █▄▄█ ▄▄▄█
                    █▀▀▄ █▀▀▀▀▀▀▄  █▀▀▀▀▀▄   ▄▀▀▀▀▀▀█ █▀▀▀▄▀▀▀█
                    █  █ █       ▄ █▄▄▄   ▄ █   ▄▄▄▄█ █   █   █
                    █  █ █   ▄   █ ▄  █   █ █     ▄   ▄▀     ▄▀
                    █  █ █  █    █ █      █ █   ▀▀▀▀█ █  █    █
                    █▄▄█ █▄▄█ ▄▄▄█ █▄▄▄▄▄▀   ▀▄▄▄▄▄▄█ █▄▄█ ▄▄▄█
                """);
            getMessage(message);
        }
        @Override
        public void getBattleIntro(String message) {
            System.out.println("""
                    █▀▀▀▀▀▀▄   ▄▀▀▀▀▀▄  █▀▀▀▄▀▀▀▄  ▄▀▀▀▀▀▀█ ▄▀▀▀▄ ▄▀▀▄   ▄▀▀▀▀▀▄  █▀▀▀▀▀▀▄\s
                    █  ▄▄▄  █ █   ▄▄  ▄ █   ▄   ▀ █   ▄▄▄▄█ █    ▀    ▄ █   ▄▄  ▄ █       ▄
                    █  ▀   ▄▀ █   ▀   █ █     ▀▄  █     ▄   █   ▄ ▄   █ █   ▀   █ █   ▄   █
                    █   █▀▀   █   ▀   █ █   █   █ █   ▀▀▀▀█ █  █ ▀ █  █ █   ▀   █ █  █    █
                    █▄▄▄█      ▀▄▄▄▄▄▀  █▄▄▄▀▄▄▄█  ▀▄▄▄▄▄▄█ █▄▄█   █▄▄█  ▀▄▄▄▄▄▀  █▄▄█ ▄▄▄█
                    █▀▀▀▀▀▄   ▄▀▀▀▀▀▄  █▀▀▀▀▀▀▀▄ █▀▀▀▀▀▀▀▄ █▀▀▄     ▄▀▀▀▀▀▀█ █▀▀▀▀▀▀▄\s
                    █      ▄ █       ▄ █       █ █       █ █  █    █   ▄▄▄▄█ █       ▄
                    █  ▀▀▀▀▄ █   ▄   █ ▀▀▀   █▀▀ ▀▀▀   █▀▀ █  █    █     ▄   █    ▄▄▀\s
                    █      █ █   █   █   █   █     █   █   █  ▀▀▀█ █   ▀▀▀▀█ █  █   ▀▄
                    █▄▄▄▄▄▀  █▄▄▄█ ▄▄█   █▄▄▄█     █▄▄▄█   █▄▄▄▄▄█  ▀▄▄▄▄▄▄█ █▄▄█ ▄▄▄█
                    """);
            getMessage(message);
        }

        @Override
        public void getEmptySpace(int number){
            for(int i = 0; i < number; i++){
                System.out.println(" ");
            }

        }

        @Override
        public void getLoadingTimer(int number){
            System.out.print("Loading -> ");
            for (int i = number; i > 0; i--) {
                try {
                    Thread.sleep(250);
                    System.out.print("◓ ");
                    Thread.sleep(250);
                    System.out.print("◒ ");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            getEmptySpace(50);

        }
}
