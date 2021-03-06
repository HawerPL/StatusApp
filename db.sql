PGDMP                         x           webAppDb    12.3    12.3     (           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            )           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            *           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            +           1262    16393    webAppDb    DATABASE     �   CREATE DATABASE "webAppDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';
    DROP DATABASE "webAppDb";
                postgres    false            �            1259    16596    applications    TABLE     �   CREATE TABLE public.applications (
    guid_app text NOT NULL,
    guid_production text NOT NULL,
    name text NOT NULL,
    description text,
    status boolean DEFAULT false NOT NULL
);
     DROP TABLE public.applications;
       public         heap    postgres    false            �            1259    24726    errors    TABLE     �   CREATE TABLE public.errors (
    guid_app text NOT NULL,
    error text NOT NULL,
    id bigint NOT NULL,
    description text,
    date timestamp without time zone NOT NULL
);
    DROP TABLE public.errors;
       public         heap    postgres    false            �            1259    24739    errors_id_seq    SEQUENCE     �   ALTER TABLE public.errors ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.errors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    16407    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    16604    productions    TABLE     j   CREATE TABLE public.productions (
    guid text NOT NULL,
    name text NOT NULL,
    description text
);
    DROP TABLE public.productions;
       public         heap    postgres    false            �            1259    16588    users    TABLE     �   CREATE TABLE public.users (
    id bigint NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    description text,
    role text DEFAULT 'user'::text NOT NULL,
    email text NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16619    users_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            !          0    16596    applications 
   TABLE DATA           \   COPY public.applications (guid_app, guid_production, name, description, status) FROM stdin;
    public          postgres    false    204   B       $          0    24726    errors 
   TABLE DATA           H   COPY public.errors (guid_app, error, id, description, date) FROM stdin;
    public          postgres    false    207   6       "          0    16604    productions 
   TABLE DATA           >   COPY public.productions (guid, name, description) FROM stdin;
    public          postgres    false    205   K                  0    16588    users 
   TABLE DATA           Q   COPY public.users (id, username, password, description, role, email) FROM stdin;
    public          postgres    false    203   �       ,           0    0    errors_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.errors_id_seq', 25, true);
          public          postgres    false    208            -           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
          public          postgres    false    202            .           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 16, true);
          public          postgres    false    206            �
           2606    16603    applications applications_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_pkey PRIMARY KEY (guid_app);
 H   ALTER TABLE ONLY public.applications DROP CONSTRAINT applications_pkey;
       public            postgres    false    204            �
           2606    24733    errors errors_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.errors
    ADD CONSTRAINT errors_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.errors DROP CONSTRAINT errors_pkey;
       public            postgres    false    207            �
           2606    16611    productions productions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.productions
    ADD CONSTRAINT productions_pkey PRIMARY KEY (guid);
 F   ALTER TABLE ONLY public.productions DROP CONSTRAINT productions_pkey;
       public            postgres    false    205            �
           2606    16613    users username_unique 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT username_unique UNIQUE (username);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT username_unique;
       public            postgres    false    203            �
           2606    16595    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    203            �
           2606    16614    applications aplications_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.applications
    ADD CONSTRAINT aplications_fkey FOREIGN KEY (guid_production) REFERENCES public.productions(guid) NOT VALID;
 G   ALTER TABLE ONLY public.applications DROP CONSTRAINT aplications_fkey;
       public          postgres    false    2716    205    204            �
           2606    24734    errors guid_app_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.errors
    ADD CONSTRAINT guid_app_fkey FOREIGN KEY (guid_app) REFERENCES public.applications(guid_app) NOT VALID;
 >   ALTER TABLE ONLY public.errors DROP CONSTRAINT guid_app_fkey;
       public          postgres    false    207    204    2714            !   �   x���1ND1D��S���N�#��8�#- -_ZA����^�n��<.��LF�IB@lT�AS&����-D1��Z�
�炮I ��$�"���^��:�����b��#��Z�Ҡ.�o�$´"Lnh5��a���@�h�kR��Z%YW�tu��a���t�ף~�~�ǧ��Ք&Cud���@���Y�����Ͳj$�5|k^)��l"-s�o]���m�ĝnP      $     x���;n�0���:�. C�HI��d�%�P؀� =~j��]��S�"N�9�7�5BbM�{a:4��r�F}���[ϷUσ^N��'� 6�C���� i�D>!�^PQ	�O�����6uW=̋��q��܀�(aS�r=�2J�eʋX�d � �`��D�:�?
k����]=%f���X%�<+�����B�Q¦X�5�����5^ ��A��(���+��KD-��PF�M�Ubq�pA[����b      "   c   x�M˱1 ��L��l�3�� i0�")"E��is��Ĥ�����+�j6��K"���r}��}��n���f�s� �ظ�)�÷�4"���E��~��          �   x�5��V�@ ���l�C�A�� F@���ďa�O�L��˫ �Ue�d��
�7{�8�핳_`�[���]>�.�M���;ֻ�2�[���;���!���$,��^�����ۀ��~��`�>������a���\�ֺ��˃���-�	cB�E9��q�gɣ�-4�N����"a_%N�j�o��Ql%�^���a�w��˘�H��&�U�     