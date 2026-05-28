# Escenario 3: VLANs, DHCP Snooping, Port Security y SSH

## Objetivo

Configurar una red en Cisco Packet Tracer con:

- VLAN 10 y VLAN 20.
- R1 como router-on-a-stick.
- DHCP seguro desde R1.
- DHCP Snooping para bloquear un DHCP atacante.
- Port Security en el puerto del PC atacante.
- SSH v2 para administración remota de los switches.

---

## Direccionamiento

```bash
VLAN 10:
Red: 192.168.10.0/24
Gateway: 192.168.10.1
Rango DHCP: 192.168.10.100 - 192.168.10.200

VLAN 20:
Red: 172.10.10.0/24
Gateway: 172.10.10.1
Rango DHCP: 172.10.10.100 - 172.10.10.200
```

---

## Puertos usados

```bash
R1 G0/0  -> S1 Fa0/1
S1 Fa0/2 -> PC0 VLAN 10
S1 Fa0/3 -> PC atacante con Port Security
S1 Fa0/4 -> S2 Fa0/1 trunk
S1 Fa0/5 -> S2 Fa0/2 trunk
S2 Fa0/3 -> PC2 VLAN 10
S2 Fa0/4 -> PC3 VLAN 20
S2 Fa0/5 -> Router DHCP atacante
```

---

# Configuración de R1

R1 funciona como router-on-a-stick y servidor DHCP seguro.

## Entrar a configuración

```bash
R1> enable
R1# configure terminal
```

## Configurar interfaz principal hacia S1

```bash
R1(config)# interface gigabitEthernet 0/0
R1(config-if)# no shutdown
R1(config-if)# exit
```

## Configurar gateway de VLAN 10

```bash
R1(config)# interface gigabitEthernet 0/0.10
R1(config-subif)# encapsulation dot1Q 10
R1(config-subif)# ip address 192.168.10.1 255.255.255.0
R1(config-subif)# exit
```

## Configurar gateway de VLAN 20

```bash
R1(config)# interface gigabitEthernet 0/0.20
R1(config-subif)# encapsulation dot1Q 20
R1(config-subif)# ip address 172.10.10.1 255.255.255.0
R1(config-subif)# exit
```

## Configurar DHCP para VLAN 10

```bash
R1(config)# ip dhcp excluded-address 192.168.10.1 192.168.10.99
R1(config)# ip dhcp excluded-address 192.168.10.201 192.168.10.254

R1(config)# ip dhcp pool VLAN10
R1(dhcp-config)# network 192.168.10.0 255.255.255.0
R1(dhcp-config)# default-router 192.168.10.1
R1(dhcp-config)# dns-server 8.8.8.8
R1(dhcp-config)# exit
```

## Configurar DHCP para VLAN 20

```bash
R1(config)# ip dhcp excluded-address 172.10.10.1 172.10.10.99
R1(config)# ip dhcp excluded-address 172.10.10.201 172.10.10.254

R1(config)# ip dhcp pool VLAN20
R1(dhcp-config)# network 172.10.10.0 255.255.255.0
R1(dhcp-config)# default-router 172.10.10.1
R1(dhcp-config)# dns-server 8.8.8.8
R1(dhcp-config)# exit
```

## Guardar configuración de R1

```bash
R1(config)# end
R1# write memory
```

---

# Configuración de S1

## Entrar a configuración

```bash
S1> enable
S1# configure terminal
```

## Crear VLANs

```bash
S1(config)# vlan 10
S1(config-vlan)# name VLAN10
S1(config-vlan)# exit

S1(config)# vlan 20
S1(config-vlan)# name VLAN20
S1(config-vlan)# exit
```

## Configurar puerto hacia R1 como trunk

```bash
S1(config)# interface fastEthernet 0/1
S1(config-if)# switchport mode trunk
S1(config-if)# switchport trunk allowed vlan 10,20
S1(config-if)# exit
```

## Configurar PC0 en VLAN 10

```bash
S1(config)# interface fastEthernet 0/2
S1(config-if)# switchport mode access
S1(config-if)# switchport access vlan 10
S1(config-if)# spanning-tree portfast
S1(config-if)# exit
```

## Configurar PC atacante con Port Security

```bash
S1(config)# interface fastEthernet 0/3
S1(config-if)# switchport mode access
S1(config-if)# switchport access vlan 10
S1(config-if)# spanning-tree portfast
S1(config-if)# switchport port-security
S1(config-if)# switchport port-security maximum 1
S1(config-if)# switchport port-security mac-address sticky
S1(config-if)# switchport port-security violation shutdown
S1(config-if)# exit
```

## Configurar troncales hacia S2

```bash
S1(config)# interface fastEthernet 0/4
S1(config-if)# switchport mode trunk
S1(config-if)# switchport trunk allowed vlan 10,20
S1(config-if)# exit

S1(config)# interface fastEthernet 0/5
S1(config-if)# switchport mode trunk
S1(config-if)# switchport trunk allowed vlan 10,20
S1(config-if)# exit
```

## Activar DHCP Snooping

```bash
S1(config)# ip dhcp snooping
S1(config)# ip dhcp snooping vlan 10,20
S1(config)# no ip dhcp snooping information option
```

## Configurar puertos confiables

```bash
S1(config)# interface fastEthernet 0/1
S1(config-if)# ip dhcp snooping trust
S1(config-if)# exit

S1(config)# interface fastEthernet 0/4
S1(config-if)# ip dhcp snooping trust
S1(config-if)# exit

S1(config)# interface fastEthernet 0/5
S1(config-if)# ip dhcp snooping trust
S1(config-if)# exit
```

## Configurar puertos no confiables

```bash
S1(config)# interface fastEthernet 0/2
S1(config-if)# no ip dhcp snooping trust
S1(config-if)# ip dhcp snooping limit rate 5
S1(config-if)# exit

S1(config)# interface fastEthernet 0/3
S1(config-if)# no ip dhcp snooping trust
S1(config-if)# ip dhcp snooping limit rate 5
S1(config-if)# exit
```

## Configurar SSH en S1

```bash
S1(config)# ip domain-name seguridad.local
S1(config)# username ADMIN secret SECRETADMIN1234
S1(config)# enable secret SECRETADMIN1234
S1(config)# crypto key generate rsa
```

Cuando solicite el tamaño de la clave, escribir:

```bash
1024
```

Continuar:

```bash
S1(config)# ip ssh version 2

S1(config)# line vty 0 15
S1(config-line)# login local
S1(config-line)# transport input ssh
S1(config-line)# exit
```

## Configurar IP de administración en S1

```bash
S1(config)# interface vlan 10
S1(config-if)# ip address 192.168.10.10 255.255.255.0
S1(config-if)# no shutdown
S1(config-if)# exit

S1(config)# ip default-gateway 192.168.10.1
```

## Guardar configuración de S1

```bash
S1(config)# end
S1# write memory
```

---

# Configuración de S2

## Entrar a configuración

```bash
S2> enable
S2# configure terminal
```

## Crear VLANs

```bash
S2(config)# vlan 10
S2(config-vlan)# name VLAN10
S2(config-vlan)# exit

S2(config)# vlan 20
S2(config-vlan)# name VLAN20
S2(config-vlan)# exit
```

## Configurar troncales hacia S1

```bash
S2(config)# interface fastEthernet 0/1
S2(config-if)# switchport mode trunk
S2(config-if)# switchport trunk allowed vlan 10,20
S2(config-if)# exit

S2(config)# interface fastEthernet 0/2
S2(config-if)# switchport mode trunk
S2(config-if)# switchport trunk allowed vlan 10,20
S2(config-if)# exit
```

## Configurar PC2 en VLAN 10

```bash
S2(config)# interface fastEthernet 0/3
S2(config-if)# switchport mode access
S2(config-if)# switchport access vlan 10
S2(config-if)# spanning-tree portfast
S2(config-if)# exit
```

## Configurar PC3 en VLAN 20

```bash
S2(config)# interface fastEthernet 0/4
S2(config-if)# switchport mode access
S2(config-if)# switchport access vlan 20
S2(config-if)# spanning-tree portfast
S2(config-if)# exit
```

## Configurar puerto hacia Router DHCP atacante

```bash
S2(config)# interface fastEthernet 0/5
S2(config-if)# switchport mode access
S2(config-if)# switchport access vlan 10
S2(config-if)# spanning-tree portfast
S2(config-if)# exit
```

## Activar DHCP Snooping

```bash
S2(config)# ip dhcp snooping
S2(config)# ip dhcp snooping vlan 10,20
S2(config)# no ip dhcp snooping information option
```

## Configurar puertos confiables

```bash
S2(config)# interface fastEthernet 0/1
S2(config-if)# ip dhcp snooping trust
S2(config-if)# exit

S2(config)# interface fastEthernet 0/2
S2(config-if)# ip dhcp snooping trust
S2(config-if)# exit
```

## Configurar puertos no confiables

```bash
S2(config)# interface fastEthernet 0/3
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit

S2(config)# interface fastEthernet 0/4
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit

S2(config)# interface fastEthernet 0/5
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit
```

## Configurar SSH en S2

```bash
S2(config)# ip domain-name seguridad.local
S2(config)# username ADMIN secret SECRETADMIN1234
S2(config)# enable secret SECRETADMIN1234
S2(config)# crypto key generate rsa
```

Cuando solicite el tamaño de la clave, escribir:

```bash
1024
```

Continuar:

```bash
S2(config)# ip ssh version 2

S2(config)# line vty 0 15
S2(config-line)# login local
S2(config-line)# transport input ssh
S2(config-line)# exit
```

## Configurar IP de administración en S2

```bash
S2(config)# interface vlan 10
S2(config-if)# ip address 192.168.10.11 255.255.255.0
S2(config-if)# no shutdown
S2(config-if)# exit

S2(config)# ip default-gateway 192.168.10.1
```

## Guardar configuración de S2

```bash
S2(config)# end
S2# write memory
```

---

# Configuración del Router DHCP Atacante

## Entrar a configuración

```bash
Router0> enable
Router0# configure terminal
```

## Configurar interfaz

```bash
Router0(config)# interface gigabitEthernet 0/0
Router0(config-if)# ip address 192.168.10.254 255.255.255.0
Router0(config-if)# no shutdown
Router0(config-if)# exit
```

## Configurar DHCP falso

```bash
Router0(config)# ip dhcp excluded-address 192.168.10.1 192.168.10.99

Router0(config)# ip dhcp pool DHCP_ATACANTE
Router0(dhcp-config)# network 192.168.10.0 255.255.255.0
Router0(dhcp-config)# default-router 192.168.100.1
Router0(dhcp-config)# dns-server 4.4.4.4
Router0(dhcp-config)# exit
```

## Guardar configuración

```bash
Router0(config)# end
Router0# write memory
```

---

# Configuración de PCs

En cada PC ir a:

```bash
Desktop > IP Configuration > DHCP
```

## Resultado esperado en VLAN 10

```bash
IP: 192.168.10.100 - 192.168.10.200
Gateway: 192.168.10.1
DNS: 8.8.8.8
```

## Resultado esperado en VLAN 20

```bash
IP: 172.10.10.100 - 172.10.10.200
Gateway: 172.10.10.1
DNS: 8.8.8.8
```

---

# Verificación

## Verificar R1

```bash
R1# show ip interface brief
R1# show ip dhcp binding
R1# show ip dhcp pool
```

## Verificar S1

```bash
S1# show vlan brief
S1# show interfaces trunk
S1# show ip dhcp snooping
S1# show ip dhcp snooping binding
S1# show port-security
S1# show port-security interface fastEthernet 0/3
S1# show ip ssh
```

## Verificar S2

```bash
S2# show vlan brief
S2# show interfaces trunk
S2# show ip dhcp snooping
S2# show ip dhcp snooping binding
S2# show ip ssh
```

## Probar SSH hacia S1

```bash
ssh -l ADMIN 192.168.10.10
```

Clave:

```bash
SECRETADMIN1234
```

## Probar SSH hacia S2

```bash
ssh -l ADMIN 192.168.10.11
```

Clave:

```bash
SECRETADMIN1234
```

---

# Resultado esperado

Las PCs de VLAN 10 deben recibir una IP del rango `192.168.10.100 - 192.168.10.200`.

Las PCs de VLAN 20 deben recibir una IP del rango `172.10.10.100 - 172.10.10.200`.

El Router DHCP atacante no debe entregar IP porque está conectado a un puerto no confiable.

El puerto del PC atacante queda protegido con Port Security.

Los switches pueden administrarse remotamente mediante SSH.

---

# Resumen

```bash
R1 funciona como router-on-a-stick y servidor DHCP seguro.
S1 y S2 manejan VLAN 10 y VLAN 20.
DHCP Snooping bloquea respuestas DHCP no autorizadas.
Los puertos troncales son trusted.
Los puertos de usuario son untrusted.
S1 Fa0/3 usa Port Security.
SSH v2 permite administración remota segura.
```