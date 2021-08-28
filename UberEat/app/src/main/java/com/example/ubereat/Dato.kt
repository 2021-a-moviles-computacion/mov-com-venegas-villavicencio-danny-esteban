package com.example.ubereat

class Dato {
    companion object{
        var listaDatos: ArrayList<Pedido> = dataPedido()


        fun dataPedido():ArrayList<Pedido> {
            //datos quemados para mostrar en el recycler view de home y search
            val list= ArrayList<Pedido>()
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))
            list.add(Pedido("KFC (Sta María La Ofelia)",1.4,"30-45 min", 4.5))

            return list
        }

    }
}