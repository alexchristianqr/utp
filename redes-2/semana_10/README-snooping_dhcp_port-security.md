# Escenario 4: DHCP Snooping con DHCP Relay y Port Security

## Objetivo

Configurar una red en Cisco Packet Tracer donde las PCs obtienen dirección IP desde un servidor DHCP seguro ubicado en otra red.

R1 reenvía las solicitudes DHCP usando `ip helper-address`.

Además, se configura DHCP Snooping en S2 para bloquear un router DHCP atacante, y Port Security para proteger el puerto del PC atacante.

---

## Direccionamiento

```text
Servidor DHCP seguro:
IP: 20.0.0.10
Máscara: 255.0.0.0
Gateway: 20.0.0.1

Red de clientes:
Red: 10.0.0.0
Máscara: 255.0.0.0
Gateway: 10.0.0.1

Router atacante:
IP: 10.0.0.150
Máscara: 255.0.0.0
```

## Objetivo

El objetivo es permitir que los clientes reciban IP únicamente desde el **servidor DHCP seguro** y bloquear las respuestas DHCP del **router atacante** mediante DHCP Snooping.

Además, se aplica **Port Security** en el puerto donde se encuentra el PC atacante para limitar el puerto a una sola dirección MAC.

---

# Configuración

## 1. Configuración de R1

R1 conecta la red del servidor DHCP seguro con la red de clientes.

```bash
R1> enable
R1# configure terminal

R1(config)# interface gigabitEthernet 0/0
R1(config-if)# ip address 20.0.0.1 255.0.0.0
R1(config-if)# no shutdown
R1(config-if)# exit

R1(config)# interface gigabitEthernet 0/1
R1(config-if)# ip address 10.0.0.1 255.0.0.0
R1(config-if)# ip helper-address 20.0.0.10
R1(config-if)# no shutdown
R1(config-if)# exit

R1(config)# end
R1# write memory
```

## 2. Configuración de S2

En S2 se configura DHCP Snooping y Port Security.

### Activar DHCP Snooping

```bash
S2> enable
S2# configure terminal

S2(config)# ip dhcp snooping
S2(config)# ip dhcp snooping vlan 1
S2(config)# no ip dhcp snooping information option
```

### Puerto hacia R1

El puerto hacia R1 debe ser confiable porque por ahí llegan las respuestas DHCP válidas.

```bash
S2(config)# interface fastEthernet 0/1
S2(config-if)# ip dhcp snooping trust
S2(config-if)# exit
```

### Puerto hacia router DHCP atacante

El puerto hacia el router atacante debe quedar como no confiable.

```bash
S2(config)# interface fastEthernet 0/5
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit
```

### Puerto del PC atacante con Port Security

```bash
S2(config)# interface fastEthernet 0/3
S2(config-if)# switchport mode access
S2(config-if)# switchport port-security
S2(config-if)# switchport port-security maximum 1
S2(config-if)# switchport port-security mac-address sticky
S2(config-if)# switchport port-security violation shutdown
S2(config-if)# exit
```

### Puertos de PCs normales

```bash
S2(config)# interface fastEthernet 0/2
S2(config-if)# switchport mode access
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit

S2(config)# interface fastEthernet 0/4
S2(config-if)# switchport mode access
S2(config-if)# no ip dhcp snooping trust
S2(config-if)# ip dhcp snooping limit rate 5
S2(config-if)# exit

S2(config)# end
S2# write memory
```

## 3. Configuración del router DHCP atacante

Este router simula un servidor DHCP no autorizado.

```bash
Router0> enable
Router0# configure terminal

Router0(config)# interface gigabitEthernet 0/0
Router0(config-if)# ip address 10.0.0.150 255.0.0.0
Router0(config-if)# no shutdown
Router0(config-if)# exit

Router0(config)# ip dhcp excluded-address 10.0.0.1 10.0.0.99

Router0(config)# ip dhcp pool DHCP_ATACANTE
Router0(dhcp-config)# network 10.0.0.0 255.0.0.0
Router0(dhcp-config)# default-router 10.0.0.150
Router0(dhcp-config)# dns-server 4.4.4.4
Router0(dhcp-config)# exit

Router0(config)# end
Router0# write memory
```

## 4. Configuración del servidor DHCP seguro

En el servidor:

```text
Desktop > IP Configuration
```

Configurar:

```text
IP Address: 20.0.0.10
Subnet Mask: 255.0.0.0
Default Gateway: 20.0.0.1
```

Luego ir a:

```text
Services > DHCP > On
```

Configurar el pool DHCP:

```text
Pool Name: CLIENTES
Default Gateway: 10.0.0.1
DNS Server: 8.8.8.8
Start IP Address: 10.0.0.100
Subnet Mask: 255.0.0.0
Maximum Number of Users: 100
```

## 5. Configuración de las PCs

En cada PC:

```text
Desktop > IP Configuration > DHCP
```

Las PCs deben recibir una IP del servidor DHCP seguro:

```text
IP: 10.0.0.100 en adelante
Gateway: 10.0.0.1
DNS: 8.8.8.8
```

---

# Verificación

## Verificar R1

```bash
R1# show ip interface brief
```

## Verificar DHCP Snooping en S2

```bash
S2# show ip dhcp snooping
S2# show ip dhcp snooping binding
```

## Verificar Port Security

```bash
S2# show port-security
S2# show port-security interface fastEthernet 0/3
```

## Pruebas desde una PC

```bash
ping 10.0.0.1
ping 20.0.0.10
```

---

# Resultado esperado

Las PCs deben obtener IP desde el servidor DHCP seguro, no desde el router atacante.

El router atacante intenta entregar direcciones IP falsas, pero sus respuestas DHCP son bloqueadas porque el puerto `Fa0/5` de S2 está configurado como **untrusted**.

El puerto `Fa0/3`, donde se encuentra el PC atacante, está protegido con **Port Security**. Si se conecta otro dispositivo o cambia la MAC permitida, el puerto entra en estado `shutdown`.

---

# Resumen

```text
R1 funciona como router principal.
El servidor DHCP seguro está en la red 20.0.0.0/8.
Los clientes están en la red 10.0.0.0/8.
R1 reenvía solicitudes DHCP usando ip helper-address.
S2 protege la red usando DHCP Snooping.
El puerto hacia R1 es trusted.
El puerto hacia el DHCP atacante es untrusted.
El puerto del PC atacante usa Port Security.
```
