<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html lang="es">
      <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Catálogo de libros</title>
        <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
      </head>

      <body class="bg-gray-50 text-gray-800">

        <main class="max-w-4xl mx-auto mt-16 px-4">

          <h1 class="text-3xl font-bold mb-8">
                        Catálogo de libros
          </h1>

          <div class="bg-white rounded-lg shadow-sm overflow-hidden">
            <table class="w-full text-left">

              <thead class="bg-gray-100">
                <tr>
                  <th class="p-4">ID</th>
                  <th class="p-4">Título</th>
                  <th class="p-4">Autor</th>
                  <th class="p-4">Precio</th>
                </tr>
              </thead>

              <tbody>
                <xsl:for-each select="biblioteca/libro">
                  <tr class="border-t border-gray-200 hover:bg-gray-50">

                    <td class="p-4">
                      <xsl:value-of select="@id"/>
                    </td>

                    <td class="p-4">
                      <xsl:value-of select="titulo"/>
                    </td>

                    <td class="p-4">
                      <xsl:value-of select="autor"/>
                    </td>

                    <td class="p-4">
                      <xsl:value-of select="format-number(precio, '0.00')"/>
                      <xsl:text> €</xsl:text>
                    </td>

                  </tr>
                </xsl:for-each>
              </tbody>

            </table>
          </div>

        </main>

      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
