module pacman {
    requires hanyaeger;
    requires org.checkerframework.checker.qual;

    exports com.github.xiangjuzou.pacman;
    exports com.github.xiangjuzou.pacman.entities.maps;

    opens audio;
    opens sprites;
    opens fonts;
    exports com.github.xiangjuzou.pacman.timers;
}
