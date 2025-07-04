PGDMP  $    .                }            vet_management_system    17.4    17.4 )    $           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            %           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            &           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            '           1262    18257    vet_management_system    DATABASE     {   CREATE DATABASE vet_management_system WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'tr-TR';
 %   DROP DATABASE vet_management_system;
                     postgres    false            �            1259    19249    animals    TABLE     '  CREATE TABLE public.animals (
    id bigint NOT NULL,
    breed character varying(255),
    colour character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(255) NOT NULL,
    species character varying(255),
    customer_id bigint NOT NULL
);
    DROP TABLE public.animals;
       public         heap r       postgres    false            �            1259    19248    animals_id_seq    SEQUENCE     �   ALTER TABLE public.animals ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    218            �            1259    19257    appointments    TABLE     �   CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    animal_id bigint NOT NULL,
    doctor_id bigint NOT NULL
);
     DROP TABLE public.appointments;
       public         heap r       postgres    false            �            1259    19256    appointments_id_seq    SEQUENCE     �   ALTER TABLE public.appointments ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    220            �            1259    19263    available_dates    TABLE     �   CREATE TABLE public.available_dates (
    id bigint NOT NULL,
    available_date date NOT NULL,
    doctor_id bigint NOT NULL
);
 #   DROP TABLE public.available_dates;
       public         heap r       postgres    false            �            1259    19262    available_dates_id_seq    SEQUENCE     �   ALTER TABLE public.available_dates ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.available_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    222            �            1259    19269 	   customers    TABLE     �   CREATE TABLE public.customers (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255)
);
    DROP TABLE public.customers;
       public         heap r       postgres    false            �            1259    19268    customers_id_seq    SEQUENCE     �   ALTER TABLE public.customers ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    224            �            1259    19277    doctors    TABLE     �   CREATE TABLE public.doctors (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap r       postgres    false            �            1259    19276    doctors_id_seq    SEQUENCE     �   ALTER TABLE public.doctors ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    226            �            1259    19285    vaccines    TABLE     �   CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    protection_finish_date date NOT NULL,
    protection_start_date date NOT NULL,
    animal_id bigint NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap r       postgres    false            �            1259    19284    vaccines_id_seq    SEQUENCE     �   ALTER TABLE public.vaccines ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    228                      0    19249    animals 
   TABLE DATA           g   COPY public.animals (id, breed, colour, date_of_birth, gender, name, species, customer_id) FROM stdin;
    public               postgres    false    218   (2                 0    19257    appointments 
   TABLE DATA           R   COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public               postgres    false    220   �3                 0    19263    available_dates 
   TABLE DATA           H   COPY public.available_dates (id, available_date, doctor_id) FROM stdin;
    public               postgres    false    222   T4                 0    19269 	   customers 
   TABLE DATA           I   COPY public.customers (id, address, city, mail, name, phone) FROM stdin;
    public               postgres    false    224   �4                 0    19277    doctors 
   TABLE DATA           G   COPY public.doctors (id, address, city, mail, name, phone) FROM stdin;
    public               postgres    false    226   �6       !          0    19285    vaccines 
   TABLE DATA           l   COPY public.vaccines (id, code, name, protection_finish_date, protection_start_date, animal_id) FROM stdin;
    public               postgres    false    228   �8       (           0    0    animals_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.animals_id_seq', 12, true);
          public               postgres    false    217            )           0    0    appointments_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.appointments_id_seq', 20, true);
          public               postgres    false    219            *           0    0    available_dates_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.available_dates_id_seq', 31, true);
          public               postgres    false    221            +           0    0    customers_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.customers_id_seq', 10, true);
          public               postgres    false    223            ,           0    0    doctors_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.doctors_id_seq', 10, true);
          public               postgres    false    225            -           0    0    vaccines_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.vaccines_id_seq', 10, true);
          public               postgres    false    227            q           2606    19255    animals animals_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public                 postgres    false    218            s           2606    19261    appointments appointments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public                 postgres    false    220            u           2606    19267 $   available_dates available_dates_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public                 postgres    false    222            w           2606    19275    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public                 postgres    false    224            {           2606    19283    doctors doctors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public                 postgres    false    226            y           2606    19293 %   customers uk2hxsqcqvp2pjgxw00gn7inubj 
   CONSTRAINT     `   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk2hxsqcqvp2pjgxw00gn7inubj UNIQUE (mail);
 O   ALTER TABLE ONLY public.customers DROP CONSTRAINT uk2hxsqcqvp2pjgxw00gn7inubj;
       public                 postgres    false    224            }           2606    19295 #   doctors ukeibxrh7nk6dkhc6c3i7pr80av 
   CONSTRAINT     ^   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT ukeibxrh7nk6dkhc6c3i7pr80av UNIQUE (mail);
 M   ALTER TABLE ONLY public.doctors DROP CONSTRAINT ukeibxrh7nk6dkhc6c3i7pr80av;
       public                 postgres    false    226                       2606    19291    vaccines vaccines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public                 postgres    false    228            �           2606    19301 (   appointments fk95vepu86o8syqtux9gkr71bhy    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public               postgres    false    4721    218    220            �           2606    19296 #   animals fkb36lt3kj4mrbdx5btxmp4j60n    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public               postgres    false    218    4727    224            �           2606    19316 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public               postgres    false    218    228    4721            �           2606    19306 (   appointments fkmujeo4tymoo98cmf7uj3vsv76    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public               postgres    false    220    226    4731            �           2606    19311 +   available_dates fknb419ilm71d71rm584rk460pk    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 U   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fknb419ilm71d71rm584rk460pk;
       public               postgres    false    222    4731    226               [  x�M��n�0���S�����պC�Um�i�..x#j��aky�h�]r����~����%K��N,���;9�����0�&�g��侀��/0*�I�M���\���;,N6ep�fP�:�t�f�n�zSƐ��l[y)KW�X	��$�a�Mr7\7� Q��%i���Vَ|��t8�q8�_T(���=W	��g�,k>{?��D��6�}�m���G�A�f�����xM�� �I&��~��9�-��zC!���6>�L���:�R��ӧ��/m%������C�T�#s�[p��E� V�ؕ�����1m�xO3�:;T�X���`����G�o�R�?���X}<(�~q���         �   x�U���!��(H`Vnl,�ǃŃ�_IP]Sk��cVt~U�	��$�P7�b�$���q�$^h�t�hIj�o�IzaݤI��Z�6��1.���e`&��c2AM4SnMU�o�̰�KP�g��$�*�;앂������q���1�_��(��\58��qp���W^=����?[�         �   x�UιC1C�X�E�:{q�u�0�f�a�����zDsd1�Q�,rb9���8���xI����׻����0Z�hI�pi�%����<Z�B�6B�h飥� ���>Z�.B�h飥�)��!�<�/ W�O'         �  x�]�Mo�0���_1�U���J5�j�Zi/.��[�#��v�ڄ�J��3�OR��CK��-Q���}�W���u�o�ӻ�|KT�6���P�o|���L1�y�Y���A��b }$X��u�����}%�7g|i�N�$e)��&�md;�񇕺R�2Щ���F�֜���3�e ��q1Y˒e�&����q��/Ԓ'�l�Z�])w���P��1�s& ���復����N���;�j|A��Y>��`9����V>����$�VJF����ߕ�ad��g�ERLF���(��ZsµU��Q�|1�*;r��5��!7�d�h��\;+�
���H��mK�z��̡�����M�#��Hƽ�e�<g%�E���\��=.ۡ�A��o��V�Խ�h��I2܏d�u^N�8f<�Rp���|Ꮝ!��A9�L^u�X����n'�|�i��F���]�         �  x�m��n�0E�ï�}��-?v����a'A]�(��D�L(�e���Rr�j��ޙ���׆�]���3������?}�\�$���tܖ�8S���P4#��@�S�e�C�T"RHU���y��°�s�ƖXC�|��M;���G�T�`�fx��e,�1l�)�̓��3$CmZ=a|0{<2���j�/�q�=^��o�����eE�	������6d*�-W��wO�h����H��Zn&dyGx�H��,�7�)5��q�K۳6��ኯ��N��t���ȻKX�%>����Gr�����|o�-ݬ��z8��H�+X��ȺK��U��0�y���$Wq3����!�#�J�a�֘�[iZ(�Z
P�)��i��Cq�#�Z$���
��C��<�mH���]�i�����_�{���D�_R��8�      !     x�m��j�@������LqFǟ�1	)�V,�Lӳ�j��O,��Fޅ��8��,ӭ�<%J��p؜����ӡ��TޔfN������;7�Xwد�/���=�gW�%9_���7�_׃͐�@��/∝����H�������9vc;t�o�.��	�l�o�ǆb��_8bU��R�rF�G�/#��Ը��pE�e�3���a���+zy���k���^�K@2���tB�m*�m�̓��_�=&��4M���]�?'��Δ3��' �ܸ��     