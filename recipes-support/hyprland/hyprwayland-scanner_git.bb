SUMMARY = "A Hyprland implementation of wayland-scanner, in and for C++."
HOMEPAGE = "https://github.com/hyprwm/hyprwayland-scanner"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=778ddc598b3f2a2da3657dda514da983"

SRC_URI = "git://github.com/hyprwm/hyprwayland-scanner.git;protocol=https;branch=main"

DEPENDS = "pugixml"

S = "${WORKDIR}/git"
SRCREV = "1270ebaa539e56d61b708c24b072b09cbbd3a828"
PV = "0.3.4"

inherit cmake pkgconfig

BBCLASSEXTEND = "native"
