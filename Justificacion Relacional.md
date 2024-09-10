# Justificacion Relacional
### Many to Many

- Colaborador con Oferta: Nos parece lógico establecer una relación MtM ya que al tener unas N cantidad Ofertas fijas dadas por las empresas asociadas, se da la situación en que una Oferta puede estar repetida en varios Colaboradores. Eso sumado a que un Colaborador puede entrar en varias Ofertas nos da un MtM, en este caso la tabla intermedia tiene el único propósito de establecer esta relación


- Registro Apertura con Vianda: En un registro apertura podemos tener diversas Viandas ingresadas, y a su vez una Vianda pudo haber sido movida varias veces por lo que se necesitan varios Registros de Apertura

- Registro Solicitud con Vianda: Mismo razonamiento que arriba, una vianda pudo haber sido parte de varias solicitudes de apertura y a su vez una sol de apertura tener varias viandas.

### Embebida con Transient

- Dirección y Ubicación: En Dirección pensamos en meter Ubicacion como una clase embebida, ya que solo la usamos cuando referenciamos Direccion, por cuestiones de implementacion la dejamos con Transient.






